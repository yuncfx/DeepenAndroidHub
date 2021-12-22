package com.ssy.coroutines4

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

private fun log(logMessage: String) = println("[${java.lang.Thread.currentThread().name}] $logMessage")

/**
 * 使用jvm参数 -Dkotlinx.coroutines.debug 打印线程所执行的协程的名字
 *
 * eg:
 * [main @coroutine#2] hello world
   [main @coroutine#3] welcome
   [main @coroutine#1] The result is 200
 */
fun main() = runBlocking {

    val a = async {
        log("hello world")
        10
    }

    val b = async {
        log("welcome")
        20
    }

    val aValue = a.await()
    val bValue = b.await()

    log("The result is ${aValue * bValue}")
}