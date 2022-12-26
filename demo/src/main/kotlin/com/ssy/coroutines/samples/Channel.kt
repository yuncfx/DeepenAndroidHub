package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Deferred values provide a convenient way to transfer a single value between coroutines.
 * Channels provide a way to transfer a stream of values.
 *
 * 协程之间传递一串数据，使用channel
 * 协程之间传递一个数据，使用Differed
 */
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
        for (x in 1..5) channel.send(x * x)
        // channel也是协程资源，需要关闭
        channel.close()
    }
    // 这里我们打印了 5 次被接收的整数：
    repeat(5) { println(channel.receive()) }
    launch {
        // 已经发送完了 就不会再打印了
        for (i in channel) {
            println("received $i")
        }
    }
    println("Done!")
    val channel2 = produce<Int> {
        send(5)
    }
}