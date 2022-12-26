package com.firstly

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
  KtHttpV3.create<ApiServiceV3>()
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
  val api: ApiServiceV3 = KtHttpV3.create<ApiServiceV3>()
  val data: RepoList = api.reposSync(lang = "Kotlin", since = "weekly")
  println(data)
}