package com.ssy.coroutines5

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

  val mySingleDispatcher = newSingleThreadContext("hello_single")
  val scope = CoroutineScope(mySingleDispatcher)

  val flow = flow {
    logX("Start")
    emit(1)
    logX("Emit: 1")
    emit(2)
    logX("Emit: 2")
    emit(3)
    logX("Emit: 3")
  }.flowOn(Dispatchers.IO)
    .filter {
      logX("Filter: $it")
      it > 2
    }.onEach {
      logX("OnEach $it")
    }.launchIn(scope)

  delay(100L)
}

