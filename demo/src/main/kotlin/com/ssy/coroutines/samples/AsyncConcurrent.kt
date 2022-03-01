package com.ssy.coroutines.samples

import coroutines.doSomethingUsefulOne
import coroutines.doSomethingUsefulTwo
import kotlinx.coroutines.*
import kotlin.system.*

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms")
}

/**
 * 如果在 concurrentSum 函数内部发生了错误，并且它抛出了一个异常， 所有在作用域中启动的协程都会被取消。
 */
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}
