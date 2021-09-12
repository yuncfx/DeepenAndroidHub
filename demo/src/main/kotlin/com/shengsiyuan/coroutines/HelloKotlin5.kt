package com.shengsiyuan.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val myJob = GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")
    // 等待myJob执行完成
    myJob.join()
    println("World")
}