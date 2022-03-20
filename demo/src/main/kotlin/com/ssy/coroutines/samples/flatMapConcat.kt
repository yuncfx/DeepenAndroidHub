package com.ssy.coroutines.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun requestFlow(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main(args: Array<String>) = runBlocking {
    val startTime = System.currentTimeMillis()
    val t: Flow<String> = (1..3).asFlow().onEach { delay(100) }.flatMapConcat { requestFlow(it) }

    t.collect { value -> // collect and print
        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
    }
}