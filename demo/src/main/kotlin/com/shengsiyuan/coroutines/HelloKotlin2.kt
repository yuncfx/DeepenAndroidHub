package com.shengsiyuan.coroutines

import kotlin.concurrent.thread

fun main() {
//    Thread(Runnable {
//        Thread.sleep(1000)
//    }).start()
    thread {
        Thread.sleep(1000)
        println("Kotlin Coroutine")
    }
    println("Hello")
    Thread.sleep(2000)
    println("World")
}