package com.firstly

interface ApiServiceV3 {
  @GET("/repo")
  fun repos(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): KtCall<RepoList>

  @GET("/repo")
  fun reposSync(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): RepoList
}