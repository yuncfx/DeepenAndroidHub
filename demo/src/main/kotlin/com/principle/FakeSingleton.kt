package com.principle

import kotlin.concurrent.thread

class FakeSingleton {
  companion object {
    val instance: FakeSingleton by lazy {
      FakeSingleton()
    }
  }
}

fun main(args: Array<String>) {
  repeat(100) {
    thread {
      val t = FakeSingleton.instance
      println("${t.hashCode()}, t:${Thread.currentThread().name}")
    }
  }
}