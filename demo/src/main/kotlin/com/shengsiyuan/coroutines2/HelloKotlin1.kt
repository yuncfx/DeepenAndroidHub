package com.shengsiyuan.coroutines2

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

/**
 * 协程的超时和取消
 *
 */

fun main() = runBlocking {

    val myJob = GlobalScope.launch {
        repeat(200) {
            println("hello $it")
            delay(500)
        }
    }

    delay(1100)

    println("hello world")

    // 同时调用cancel和join
    myJob.cancel(CancellationException("cancel by myself"))
    myJob.join()

//    myJob.cancelAndJoin()

    println("welcome")
}