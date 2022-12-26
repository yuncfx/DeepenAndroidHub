package com.ssy.coroutines.samples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun main() = runBlocking {
  val t = Mutex()
  var i = 0
  val jobs = mutableListOf<Job>()
  val time = System.currentTimeMillis()
  repeat(10) {
    val job = launch(Dispatchers.IO) {
      repeat(1000) {
        t.withLock {
          i++
        }
      }
    }
    jobs.add(job)
  }

  jobs.joinAll()
  println("duration: ${System.currentTimeMillis() - time}")
  println(i)

  t()
}

suspend fun t() {
  coroutineScope {
    val result = (1..10).map {
      async(Dispatchers.Default) {
        var i = 0
        repeat(1000) {
          i++
        }
        return@async i
      }
    }.awaitAll().sum()

    println("result:$result")
  }
}