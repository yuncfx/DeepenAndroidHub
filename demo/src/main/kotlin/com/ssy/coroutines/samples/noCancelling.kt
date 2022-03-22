package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * 协程取消是合作的（cooperative）。 协程代码必须是合作的才能取消。
 * kotlinx.coroutines 中的所有挂起函数都是可以取消的。他们检查协程的取消并在取消时抛出 CancellationException。
 * 但是，如果协程在【计算工作】并且不检查取消，则不能取消它，如下例所示：
 */
fun main(args: Array<String>) = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) {
//              yield() // 可以加yield()函数或检查挂起状态isActive来帮助退出
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++}...")
                nextPrintTime += 500L
            }
        }
    }

    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}