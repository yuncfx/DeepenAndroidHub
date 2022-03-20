package com.ssy.coroutines.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    // cancel the requestFlow of old value if requestFlow is a suspend function
//    1: First at 186 ms from start
//    2: First at 304 ms from start
//    3: First at 421 ms from start
//    3: Second at 923 ms from start
    (1..3).asFlow().onEach { delay(100) }.flatMapLatest {
        requestFlow(it)
    }.collect { value -> // collect and print
        println("$value at ${System.currentTimeMillis() - startTime} ms from start")
    }
}