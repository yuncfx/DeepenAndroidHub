package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Conceptually, a close is like sending a special close token to the channel.
 * The iteration stops as soon as this close token is received, so there is a guarantee that
 * all previously sent elements before the close are received:
 */
fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close() // 我们结束发送
    }
    // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
    for (y in channel) println(y)
    println("Done!")
}