package com.ssy.coroutines.samples

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

// 代码段13

fun main() = runBlocking {
  // 变化在这里
  val channel: ReceiveChannel<Int> = produce(capacity = 3) {
    // 变化在这里
    (1..5).forEach {
      send(it)
      println("Send $it")
    }
  }

  // isClosedForReceive会崩溃
//  while (!channel.isClosedForReceive) {
//    val i = channel.receive()
//    println("Receive $i")
//  }

  channel.consumeEach {
    println("Receive $it")
  }

  println("end")
}

/*
输出结果
// 省略部分
Receive 300
Send 300
ClosedReceiveChannelException: Channel was closed
*/