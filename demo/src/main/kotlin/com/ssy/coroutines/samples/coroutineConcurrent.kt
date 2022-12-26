package com.ssy.coroutines.samples

import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

// 单线程并发
fun main() = runBlocking {
  val mySingleDispatcher = Executors.newSingleThreadExecutor {
    Thread(it, "MySingleThread").apply { isDaemon = true }
  }.asCoroutineDispatcher()

  var i = 0
  val jobs = mutableListOf<Job>()

  repeat(10) {
    val job = launch(mySingleDispatcher) {
      repeat(1000) {
        // 单线程并发， 耗时不会变少
        delay(3)
        i++
      }
    }
    jobs.add(job)
  }

  jobs.joinAll()

  println("i = $i")
}
