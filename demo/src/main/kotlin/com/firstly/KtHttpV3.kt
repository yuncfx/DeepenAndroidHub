package com.firstly

import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`.getRawType
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy

object KtHttpV3 {
  private var okHttpClient: OkHttpClient = OkHttpClient()
  private var gson: Gson = Gson()

  var baseUrl = "https://baseurl.com"

  fun <T: Any> create(service: Class<T>): T {
    return Proxy.newProxyInstance(
      service.classLoader,
      arrayOf<Class<*>>(service)
    ) { proxy, method, args ->
      val annotations = method.annotations
      for (annotation in annotations) {
        if (annotation is GET) {
          val url = baseUrl + annotation.value
          return@newProxyInstance invoke<T>(url, method, args!!)
        }
      }
      return@newProxyInstance null

    } as T
  }

  fun <T:Any> invoke(path: String, method: Method, args: Array<Any>): Any? {

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
    val call = okHttpClient.newCall(request)


    return if (isKtCallReturn(method)) {
      val genericReturnType = getTypeArgument(method)
      KtCall<T>(call, gson, genericReturnType)
    } else {
      val response = okHttpClient.newCall(request).execute()
      val genericReturnType = method.genericReturnType
      val json = response.body?.string()
      gson.fromJson<Any>(json, genericReturnType)
    }

  }

  private fun isKtCallReturn(method:Method) =
    getRawType(method.genericReturnType) == KtCall::class.java

  private fun getTypeArgument(method: Method) =
    (method.genericReturnType as ParameterizedType).actualTypeArguments[0]
}


// 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