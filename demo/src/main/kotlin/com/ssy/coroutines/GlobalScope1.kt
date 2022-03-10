package com.ssy.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("hello")

    runBlocking {
        delay(2000)
    }

    println("world")
}