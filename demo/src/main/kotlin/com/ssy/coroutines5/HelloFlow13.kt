package com.ssy.coroutines5

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.CoroutineContext

/**
 * Flow Context （Flow上下文）
 *
 * Flow的收集动作总是发生在调用协程的上下文当中, 这个特性叫做Context Preservation（上下文保留）
 * 所以默认情况下， 产生flow流的地方，也运行在collect执行的上下文中，以避免资源切换的浪费
 *
 * This is the perfect default for fast-running or asynchronous code that does not care about
 * the execution context and does not block the caller.
 */
private fun log(msg: String) = println("[${Thread.currentThread().name}, $msg]")

private fun myMethod(): Flow<Int> = flow {
    log("started")
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking {
    myMethod().collect {
        log("Collected: $it")
    }

    withContext(Dispatchers.IO) {
        myMethod().collect {
            log("Collected 2: $it")
        }
    }
}
