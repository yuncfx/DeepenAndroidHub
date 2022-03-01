package com.ssy.coroutines.samples

import coroutines.massiveRun
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

val counter2 = AtomicInteger()

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            counter2.incrementAndGet()
        }
    }
    println("Counter = $counter2")
}