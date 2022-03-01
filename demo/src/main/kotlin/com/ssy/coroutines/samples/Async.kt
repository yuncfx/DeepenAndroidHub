package com.ssy.coroutines.samples

import coroutines.doSomethingUsefulOne
import coroutines.doSomethingUsefulTwo
import coroutines.logThread
import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        logThread("time")
        val one = async {
            doSomethingUsefulOne()
        }
        val two = async {
            doSomethingUsefulTwo()
        }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}