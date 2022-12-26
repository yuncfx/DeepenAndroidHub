package com.ssy.coroutines5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking

fun logX(msg: String) {
  println("msg:$msg ${Thread.currentThread().name}")
}

fun main() = runBlocking {
  val flow = flow {
    logX("Start")
    emit(1)
    logX("Emit: 1")
    emit(2)
    logX("Emit: 2")
    emit(3)
    logX("Emit: 3")
  }

  flow.filter {
    logX("Filter: $it")
    it > 2
  }.flowOn(Dispatchers.IO)
    .collect {
      logX("Collect $it")
    }
}

