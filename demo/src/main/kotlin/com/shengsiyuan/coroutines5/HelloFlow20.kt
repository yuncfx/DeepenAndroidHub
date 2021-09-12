package com.shengsiyuan.coroutines5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * Flow exception
 */
private fun myMethod(): Flow<Int> = flow<Int> {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking {
    try {
        myMethod().collect { value ->
            println(value)
            check(value <= 1) {
                "Collected $value"
            }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}