package com.ssy.coroutines4

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使用JVM参数:-Dkotlinx.coroutines.debug
 */
fun main() = runBlocking<Unit>(CoroutineName("myCoroutine")) {
    launch(Dispatchers.Default + CoroutineName("helloWorld")) {
        println("thread:${Thread.currentThread().name}")
    }

    println("thread:${Thread.currentThread().name}")
}