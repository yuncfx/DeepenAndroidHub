package com.ssy.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

/**
 * 只有当调用了Flow对象上的终止操作（如collect）之后，Flow才会真正执行
 * 当我们调用了myMethod方法后，它实际上是立刻返回的，并不会去执行其中的代码。
 */
private fun myMethod(): Flow<Int> = flow {
    println("myMethod executed")
    for (i in 1..3) {
        delay(100)
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    println("hello")
    val flow = myMethod()
    println("world")
    flow.collect {
        println(it)
    }

    println("welcome")

    flow.collect {
        println(it)
    }
}