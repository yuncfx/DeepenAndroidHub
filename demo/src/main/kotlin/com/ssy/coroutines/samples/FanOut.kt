package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    val producer = produceNumbers2()
    repeat(5) { launchProcessor(it, producer) }
    delay(950)
    producer.cancel() // 取消协程生产者从而将它们全部杀死
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers2() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // 产生下一个数字
        delay(100) // 等待 0.1 秒
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}