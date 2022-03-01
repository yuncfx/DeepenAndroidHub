package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

fun simple5(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // 假装我们异步等待了 100 毫秒
        emit(i) // 发射下一个值
    }
}

fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simple5()
            .buffer() // 缓冲发射项，无需等待
            .collect { value ->
                delay(300) // 假装我们花费 300 毫秒来处理它
                println(value)
            }
    }
    println("Collected in $time ms")
}