package com.ssy.coroutines4

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

suspend fun doSomethingUsefulOne(): Int {
  delay(3000L)
  return 13
}

suspend fun testAsyncAwait(n: Int) = coroutineScope {
  val one = async {
    doSomethingUsefulOne()
  }
  println("start $n ->" + System.currentTimeMillis() / 1000)
  // 执行结果可以看到， await不会阻塞当前线程，但是代码还是按顺序执行的，因为await并不是启动了协程
  one.await()
  println("end $n ->" + System.currentTimeMillis() / 1000)
}

suspend fun main() = coroutineScope {
  val context = newSingleThreadContext("MyOwnThread")
  repeat(2) {
    launch(context) { testAsyncAwait(it) }
  }
}