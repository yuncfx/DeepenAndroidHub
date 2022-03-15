package com.ssy.coroutines.samples

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    // 同时指定Dispatcher和协程name
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
}