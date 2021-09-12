package com.shengsiyuan.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")
    Thread.sleep(2000)
    println("World")
}