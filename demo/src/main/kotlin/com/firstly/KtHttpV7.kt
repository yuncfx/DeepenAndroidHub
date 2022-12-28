package com.firstly

import com.google.gson.Gson
import com.google.gson.internal.`$Gson$Types`.getRawType
import com.ssy.coroutines5.logX
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.Request
import test.puzzler.Api
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy

fun <T: Any> KtCall<T>.asFlow(): Flow<T> = callbackFlow {
  val call = call(object: Callback<T> {
    override fun onSuccess(data: T) {
      trySendBlocking(data).onSuccess {
        close()
      }.onFailure {
        cancel(CancellationException("Send channel fail!", it))
        // close(it)
      }
    }

    override fun onFail(throwable: Throwable) {
      cancel(CancellationException("Request fail!", throwable))
      // close(throwable)
    }
  })

  awaitClose {
    call.cancel()
  }
}

/**
 * callbackFlow{}，它的作用就是把 Callback 转换成 Flow。它的底层其实用到了 Channel，因此，我们可以在 callbackFlow{} 当中调用 trySend()、trySendBlocking()，这两个方法都是 Channel 当中的“非挂起函数”的方法。需要注意的是，这里我们不能直接使用 Channel 的挂起函数 send()，因为它必须要在协程体当中执行。
 * 在 callbackFlow{} 里，出现异常的逻辑分支当中，如果我们需要关闭 callbackFlow，那么在调用 close() 的时候，一定要传入对应的异常参数 close(throwable)。不然的话，Flow 的下游就无法收到任何的异常信息。
 * 在 callbackFlow{} 当中创建的协程任务，也可以跟随 callbackFlow 一同被取消，只要我们不打破它原有的协程父子关系。由于 Flow 的上游、中间操作符不需要协程作用域，因此，我们可以在非协程当中执行创建 Flow。这就导致我们 6.0 版本的代码轻松就可以实现。
 */
object KtHttpV5 {
  private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient()
  }

  private val gson: Gson by lazy {
    Gson()
  }

  var baseUrl = "https://baseUrl.com"

  fun <T:Any> create(service: Class<T>):T {
    return Proxy.newProxyInstance(
      service.classLoader,
      arrayOf<Class<*>>(service)
    ) { proxy, method, args ->
      val annotations = method.annotations
      for (annotation in annotations) {
        if (annotation is GET) {
          val url  = baseUrl + annotation.value
          return@newProxyInstance invoke<T>(url, method, args!!)
        }
      }
      return@newProxyInstance null
    } as T
  }

  private fun <T:Any> invoke(path:String, method: Method, args: Array<Any> ): Any? {
    if (method.parameterAnnotations.size != args.size) return null

    var url = path
    val parameterAnnotations = method.parameterAnnotations
    for (i in parameterAnnotations.indices) {
      for (parameterAnnotation in parameterAnnotations[i]) {
        if (parameterAnnotation is Field) {
          val key = parameterAnnotation.value
          val value = args[i].toString()
          if (!url.contains("?")) {
            url  += "?$key=$value"
          } else {
            url += "&$key=$value"
          }
        }
      }
    }

    val request = Request.Builder().url(url).build()

    val call = okHttpClient.newCall(request)
    return when {
      isKtCallReturn(method) -> {
        val genericReturnType = getTypeArgument(method)
        KtCall<T>(call, gson, genericReturnType)
      }

      isFlowReturn(method) -> {
        logX("Start out")
        flow<T> {
          logX("Start in")
          val genericReturnType = getTypeArgument(method)
          val response = okHttpClient.newCall(request).execute()
          val json = response.body?.string()
          val result = gson.fromJson<T>(json, genericReturnType)
          logX("Start emit")
          emit(result)
          logX("End emit")
        }
      }

      else -> {
        val response = okHttpClient.newCall(request).execute()

        val genericReturnType = method.genericReturnType
        val json = response.body?.string()
        gson.fromJson<T>(json, genericReturnType)
      }
    }
  }

  private fun getTypeArgument(method: Method) =
    (method.genericReturnType as ParameterizedType).actualTypeArguments[0]

  private fun isKtCallReturn(method: Method) =
    getRawType(method.genericReturnType) == KtCall::class.java

  private fun isFlowReturn(method: Method) =
    getRawType(method.genericReturnType) == Flow::class.java
}

fun main(): Unit = runBlocking {
  testFlow()
}

private suspend fun testFlow() =
  KtHttpV5.create(ApiServiceV5::class.java)
    .repos(lang="Kotlin", since="weekly")
    .asFlow()
    .catch { println("Catch: $it")}
    .collect {
      println(it)
    }

private suspend fun testFlow2()  =
  KtHttpV5.create(ApiServiceV5::class.java)
    .reposFlow(lang = "Kotlin", since="weekly")
    .flowOn(Dispatchers.IO)
    .catch { println("Catch :$it") }
    .collect {
      logX("${it.account}")
    }