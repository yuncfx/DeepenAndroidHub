package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * Job是Context的一部分，并且可以通过coroutineContext[Job]的方式得到
 */
fun main() = runBlocking<Unit> {
    println("My job is ${coroutineContext[Job]}")
}