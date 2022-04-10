package com.ssy.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flatten Flow
 * Flow<Flow<Int>> -> Flow<Int>
 *
 * TODO 没看懂 flatMapConcat 和 flatMapMerge的区别
 */
private fun myMethod(i: Int): Flow<String> = flow {
  emit("$i: First")
  delay(300)
  emit("$i: Second")
}

fun main() = runBlocking<Unit> {
  val startTime = System.currentTimeMillis()
  val t: Flow<Int> = (1..3).asFlow().onEach {
    delay(100)
  }

  t.flatMapMerge { myMethod(it) }.collect { value ->
    println("flatMapMerge $value at ${System.currentTimeMillis() - startTime} ms")
  }

  t.flatMapConcat { myMethod(it) }.collect { value ->
    println("flatMapConcat $value at ${System.currentTimeMillis() - startTime} ms")
  }
}