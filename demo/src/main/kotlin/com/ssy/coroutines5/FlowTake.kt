package com.ssy.coroutines5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking

/**
 * 限定数量的中间操作
 *
 * take操作其实是通过异常来实现的(AbortFlowException)， finally也会执行。
 *
 */
fun myNumbers(): Flow<Int> = flow {
  try {
    emit(1)
    emit(2)
    println("hello world")
    emit(3)
  } catch (e: Exception) {
    println(e)
  } finally {
    println("finally")
  }
}

fun main() = runBlocking {
  myNumbers().take(2).collect { println(it) }
}