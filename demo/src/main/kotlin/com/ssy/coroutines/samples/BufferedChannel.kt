package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    val channel = Channel<Int>(4) // 启动带缓冲的通道
    val sender = launch { // 启动发送者协程
        repeat(10) {
            println("Sending $it") // 在每一个元素发送前打印它们
            channel.send(it) // 将在缓冲区被占满时挂起
        }
    }
    // 没有接收到东西……只是等待……
    delay(1000)
    sender.cancel() // 取消发送者协程
}