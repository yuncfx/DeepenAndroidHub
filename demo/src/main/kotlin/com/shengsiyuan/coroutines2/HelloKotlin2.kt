package com.shengsiyuan.coroutines2

import kotlinx.coroutines.*


/**
 * kotlinx.coroutines包下的所有挂起函数都是可取消的
 * 他们会检查协程的取消状态，当取消时就会抛出CancellationException异常
 * 不过，如果协程正在处于某个计算过程中， 并且没有检查取消状态，那么它就是无法被取消的。
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime

        var i = 0

        while (i < 20) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("Job: I am sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }

    delay(1300)
    println("hello world")

    job.cancelAndJoin()
    println("welcome")
}