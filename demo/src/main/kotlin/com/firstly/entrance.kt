package com.firstly

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {

  KtHttpV1.baseUrl = "https://api.github.com"

  val api: ApiService = KtHttpV1.create(ApiService::class.java)
  val data: RepoList = api.repos(lang = "Kotlin", since = "weekly")
  println(data)

  // val api2:GitHubService = KtHttpV1.create(GitHubService::class.java)
  // val data2:User = api2.search(id ="JetBrains")

  KtHttpV2.baseUrl = "https://api.github.com"
  val api2: ApiService = KtHttpV2.create()
  val data2: RepoList = api2.repos(lang = "Kotlin", since = "weekly")
  println(data2)
}

private fun testAsync() {
  KtHttpV3.create(ApiServiceV3::class.java)
    .repos(
      lang = "Kotlin",
      since = "weekly"
    ).call(object : Callback<RepoList> {
      override fun onSuccess(data: RepoList) {
        println(data)
      }

      override fun onFail(throwable: Throwable) {
        println(throwable)
      }
    })
}

private fun testSync() {
  val api: ApiServiceV3 = KtHttpV3.create<ApiServiceV3>(ApiServiceV3::class.java)
  val data: RepoList = api.reposSync(lang = "Kotlin", since = "weekly")
  println(data)
}

private fun testAwait(cancellable:Boolean) = runBlocking {
  val start = System.currentTimeMillis()
  val deferred = async {
    if (cancellable) {
      KtHttpV4.create(ApiServiceV3::class.java)
        .repos(lang = "Kotlin", since = "weekly")
        .awaitCancellable()
    } else {
      KtHttpV4.create(ApiServiceV3::class.java)
        .repos(lang = "Kotlin", since = "weekly")
        .await()
    }
  }

  deferred.invokeOnCompletion {
    println("invokeOnCompletion")
  }

  delay(50)

  deferred.cancel()
  println("Time cancel: ${System.currentTimeMillis() - start}")

  try {
    println(deferred.await())
  } catch (e: Exception) {
    println("Time exception: ${System.currentTimeMillis() - start}")
    println("Catch exception:$e")
  } finally {
    println("Time total: ${System.currentTimeMillis() - start}")
  }
}