package com.ssy.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 协程完成类似的效果
 * 1. 不会阻塞主线程
 * 2. 计算结果会一次性返回给调用端
 */
private suspend fun myMethod(): List<String> {
    delay(1000)
    return listOf("hello", "world", "hello world")
}

fun main() = runBlocking {
    myMethod().forEach { println(it) }
}