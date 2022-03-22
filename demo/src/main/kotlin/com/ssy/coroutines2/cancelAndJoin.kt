package com.ssy.coroutines2

import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

/**
 * 协程的超时和取消
 *
 */

@OptIn(DelicateCoroutinesApi::class)
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

    val myJob2 = launch {
        repeat(200) {
            println("hello myJob2 $it")
            // delay是suspend函数，会自动检查取消状态
            delay(100)
        }
    }
    delay(300)
    myJob2.cancelAndJoin()
    println("welcome myJob2")
}