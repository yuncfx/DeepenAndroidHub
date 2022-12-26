package com.firstly

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.Proxy

object KtHttpV1 {

  private var okHttpClient: OkHttpClient = OkHttpClient()
  private var gson: Gson = Gson()

  var baseUrl = "https://baseurl.com"

  fun <T> create(service: Class<T>): T {
    return Proxy.newProxyInstance(
      service.classLoader,
      arrayOf<Class<*>>(service)
    ) { proxy, method, args ->
      val annotations = method.annotations
      for (annotation in annotations) {
        if (annotation is GET) {
          val url = baseUrl + annotation.value
          return@newProxyInstance invoke(url, method, args)
        }
      }

      return@newProxyInstance null
    } as T
  }

  private fun invoke(path: String, method: Method, args: Array<Any>): Any? {

    if (method.parameterAnnotations.size != args.size) return null

    var url = path

    val parameterAnnotations = method.parameterAnnotations
    for (i in parameterAnnotations.indices) {
      for (parameterAnnotation in parameterAnnotations[i]) {
        if (parameterAnnotation is Field) {
          val key = parameterAnnotation.value
          val value = args[i].toString()
          url += if (!url.contains("?")) {
            "?$key=$value"
          } else {
            "&$key=$value"
          }
        }
      }
    }

    val request = Request.Builder().url(url).build()
    val response = okHttpClient.newCall(request).execute()

    val genericReturnType = method.genericReturnType
    val body = response.body
    val json = body?.string()

    return gson.fromJson(json, genericReturnType)
  }
}