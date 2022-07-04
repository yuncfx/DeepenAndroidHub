package com.ssy.coroutines4

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun getUserInfo(): String {
  println("userInfo start ${Thread.currentThread().name}")
  withContext(Dispatchers.IO) {
    println("userInfo withContext IO ${Thread.currentThread().name}")
    delay(100)
  }

  withContext(Dispatchers.Default){
    println("userInfo withContext Default ${Thread.currentThread().name}")
    delay(100)
  }

  withContext(Dispatchers.Unconfined) {
    println("userInfo withContext Unconfined ${Thread.currentThread().name}")
    delay(100)
  }
  println("userInfo exit ${Thread.currentThread().name}")
  return "haha"
}

fun main() = runBlocking<Unit> {
  GlobalScope.launch {
    println("coroutine start ${Thread.currentThread().name}")
    delay(1000)
    val result = getUserInfo()
    println("coroutine exit ${Thread.currentThread().name}")
  }

  delay(3000L)
}