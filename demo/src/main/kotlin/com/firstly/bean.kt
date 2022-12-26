package com.firstly

data class RepoList(
  var account: Int?,
  var items: List<Repo>?,
  var msg: String?
)

data class Repo(
  var added_stars: String?,
  var avatars: List<String>?,
  var desc: String?,
  var forks: String?,
  var lang: String?,
  var repo: String?,
  var repo_link: String?,
  var stars: String?
)

data class User(var id: String, var name: String)