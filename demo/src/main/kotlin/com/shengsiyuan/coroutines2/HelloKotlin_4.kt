package com.shengsiyuan.coroutines2

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 使用finally来关闭资源
 * join与cancelAndJoin都会等待所有清理动作完成才会继续往下执行
 */
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(100) { i ->
                println("job: I am sleeping $i")
                delay(500)
            }
        } finally {
            println("执行了finally块")
        }
    }

    delay(1300)
    println("hello world")

    job.cancelAndJoin()
    println("welcome")
}