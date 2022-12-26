package com.ssy.coroutines.samples

import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// 代码段12

fun main() = runBlocking {
  val deferreds = mutableListOf<Deferred<Int>>()

  val t = System.currentTimeMillis()
  println("t:$t")

  repeat(10) {
    val deferred = async(Dispatchers.Default, start = LAZY) {
      var i = 0
      delay(1000)
      repeat(1000) {
        i++
      }
      return@async i
    }
    deferreds.add(deferred)
  }

  var result = 0
  deferreds.forEach {
    result += it.await()
  }

  val duration = System.currentTimeMillis() - t

  println("i = $result, duration:$duration")
}