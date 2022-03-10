package com.ssy.coroutines

import kotlin.concurrent.thread


fun main() {
    // OOM unable to create new native thread, 可能出现，但本机未出现，打印时间比协程长很多
    repeat(5000) {
        thread {
            Thread.sleep(1000)
            println("A")
        }
    }

    println("Hello World")
}