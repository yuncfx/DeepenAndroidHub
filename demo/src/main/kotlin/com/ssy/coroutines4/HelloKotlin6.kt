package com.ssy.coroutines4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 关于父子协程之间的关系
 * 当一个协程是通过另外一个协程的CoroutineScope来启动的，那么这个协程
 * 就会通过CoroutineScope.coroutineContext来继承其上下文信息，
 * 同时，新协程的Job就会成为父协程Job的一个孩子；当父协程被取消执行时，
 * 该父协程的所有孩子都会通过递归的方式一并取消执行。
 *
 * 特例情况：当我们使用GlobalScope来启动协程时，对于启动的新协程来说，其
 * Job是没有父Job的。因此，它就不会绑定到其所启动的那个范围上，故其可以独
 * 立执行（这是一种特殊情况）
 */

fun main() = runBlocking<Unit> {
    val request = launch {
        GlobalScope.launch {
            println("Job1: hello") //1
            delay(1000)
            println("Job1:world") //3
        }

        launch {
            delay(100)
            println("Job2: hello") // 2
            delay(1000)
            println("Job2: world")
        }
    }

    delay(500)
    request.cancel()
    delay(1000)
    println("welcome") //4
}