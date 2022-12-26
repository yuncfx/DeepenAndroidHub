package com.ssy.coroutines.samples

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChannelModel {
  private val _channel: Channel<Int> = Channel()
  val channel: ReceiveChannel<Int> by ::_channel

  suspend fun init() {
    (1..3).forEach {
      _channel.send(it)
    }
  }
}

fun main() = runBlocking {
  val model = ChannelModel()
  launch {
    model.init()
  }

  model.channel.consumeEach {
    println(it)
  }
}