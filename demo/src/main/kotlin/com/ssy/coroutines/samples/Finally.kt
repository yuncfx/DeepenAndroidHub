package com.ssy.coroutines.samples

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
            /**
             * 在 finally 块中调用挂起函数的行为都会抛出 CancellationException，因为这里持续运行的代码是可以被取消的。
             */
            launch {
                println("我在搞点事")
                delay(1000L)
                println("搞事结束")
            }
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消该作业并且等待它结束
    println("main: Now I can quit.")
}