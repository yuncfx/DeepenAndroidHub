package com.shengsiyuan.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flatten Flow
 * Flow<Flow<Int>> -> Flow<Int>
 *
 * TODO Ã»¿´¶® flatMapConcat
 */
private fun myMethod(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()

    (1..3).asFlow().onEach {
        delay(100)
    }.flatMapConcat { myMethod(it) }.collect {
        value ->
        println("$value at ${System.currentTimeMillis() - startTime} ms")
    }
}