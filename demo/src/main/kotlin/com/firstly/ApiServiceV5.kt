package com.firstly

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

interface ApiServiceV5 {
  @GET("/repo")
  fun repos(
    @Field("lang") lang:String,
    @Field("since") since:String
  ): KtCall<RepoList>

  @GET("/repo")
  fun reposFlow(
    @Field("lang") lang:String,
    @Field("since") since:String
  ): Flow<RepoList>
}

