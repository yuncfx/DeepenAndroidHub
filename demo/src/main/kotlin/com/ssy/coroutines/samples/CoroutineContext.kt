package com.ssy.coroutines.samples

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    println("main                  : I'm working in thread ${Thread.currentThread().name}")
    launch { // 运行在父协程的上下文中，即 runBlocking 主协程
        println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
        println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) { // 将会获取默认调度器
        println("Default               : I'm working in thread ${Thread.currentThread().name}")
    }
    val newSingleThreadContext = newSingleThreadContext("MyOwnThread")

    launch(newSingleThreadContext) { // 将使它获得一个新的线程
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
    newSingleThreadContext.close()
}