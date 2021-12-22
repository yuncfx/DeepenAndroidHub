package com.ssy.coroutines4

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 对于父子协程来说， 父协程总是会等待其所有子协程的完成。
 * 对于父协程来说，它不必显式地去追踪由它锁启动的所有子协程，同时也不必去调用子协程的Job.join()方法来等待子协程的完成
 */
fun main() = runBlocking<Unit> {
    val request = launch {
        repeat(5) { i ->
            launch {
                delay((i + 1) * 100L)
                println("Coroutine $i finished")
            }
        }
        println("hello")
    }

    request.join()
    println("world")
}