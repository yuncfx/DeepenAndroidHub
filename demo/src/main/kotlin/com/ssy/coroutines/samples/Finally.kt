@file:OfficialRelative
package com.ssy.coroutines.samples

import com.ssy.OfficialRelative
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
             * 在 finally 块中调用挂起函数的行为都会抛出 CancellationException，因为执行到这里时协程已经被取消了。
             */
            launch {
                println("我在搞点事") // never print
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