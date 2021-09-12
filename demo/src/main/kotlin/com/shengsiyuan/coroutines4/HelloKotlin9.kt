package com.shengsiyuan.coroutines4

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使用JVM参数:-Dkotlinx.coroutine.debug
 */
fun main() = runBlocking<Unit>(CoroutineName("myCoroutine")) {
    launch(Dispatchers.Default + CoroutineName("helloWorld")) {
        println("thread:${java.lang.Thread.currentThread().name}")
    }

    println("thread:${java.lang.Thread.currentThread().name}")
}