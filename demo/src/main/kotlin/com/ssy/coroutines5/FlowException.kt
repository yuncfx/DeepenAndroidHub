package com.ssy.coroutines5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

/**
 * flow的异常 从发射阶段，中间阶段 终止阶段都可以捕获异常。
 */
private fun myMethod(): Flow<String> = flow {
  for (i in 1..3) {
    println("Emitting $i")
    emit(i)
  }
}.map { value ->
  check(value <= 1) { "Crash on $value" }
  "string $value"
}

fun main() = runBlocking {
  try {
    myMethod().collect { println(it) }
  } catch (e: Throwable) {
    println("Caught $e")
  }
}