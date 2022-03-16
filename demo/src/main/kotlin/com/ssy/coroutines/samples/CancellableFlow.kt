package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun simple3(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100)
        println("Emitting $i")
        emit(i)
    }
}

/*
 * Flow adheres to the general cooperative cancellation of coroutines. As usual, flow collection
 * can be cancelled when the flow is suspended in a cancellable suspending function
 */
fun main() = runBlocking<Unit> {
    withTimeoutOrNull(250) { // 在 250 毫秒后超时
        simple3().collect { value -> println(value) }
    }
    println("Done")
}