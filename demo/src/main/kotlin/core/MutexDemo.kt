package core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// 代码段13

fun main() = runBlocking {
  val result: Int
  val time = measureTimeMillis {
    result = (1..10).map {
      async(Dispatchers.Default) {
        var i = 0
        delay(1000)
        repeat(1000) {
          i++
        }
        return@async i
      }
    }.awaitAll()
      .sum()
  }

  println("i = $result, time:$time")
}