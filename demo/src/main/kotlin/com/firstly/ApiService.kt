package com.firstly

interface ApiService {
  @GET("/repo")
  fun repos(
    @Field("lang") lang: String,
    @Field("since") since: String
  ): RepoList
}

interface GitHubService {
  @GET("/search")
  fun search(
    @Field("id") id: String
  ): User
}