package com.ssy.chapter1

fun main() {
  var string: String? = null

  string?.let {
    println("it's not null")
  } ?: println("it's null")

  val t = string?.let {
    println("it's not null")
  }
}