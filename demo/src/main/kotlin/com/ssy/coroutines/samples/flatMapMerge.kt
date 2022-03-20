package com.ssy.coroutines.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * flatMapMerge 支持并发
 * @see flatMapConcat
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    (1..3).asFlow().onEach { delay(100) }.flatMapMerge { requestFlow(it) }
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }

    // map + flattenMerge == flatMapMerge
    (4..6).asFlow().onEach { delay(100) }.map { requestFlow(it) }.flattenMerge()
        .collect { value -> // collect and print
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}