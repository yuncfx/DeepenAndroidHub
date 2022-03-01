package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// 模仿事件流
fun events(): Flow<Int> = (1..3).asFlow().onEach { delay(100) }

fun main() = runBlocking<Unit> {
    events()
        .onEach { event -> println("Event: $event") }
        .collect() // <--- 等待流收集
    println("Done 111")

    // launchIn 末端操作符可以在这里派上用场。使用 launchIn 替换 collect 我们可以在单独的协程中启动流的收集，这样就可以立即继续进一步执行代码
    events()
        .onEach { event -> println("Event: $event") }
        .launchIn(this) // <--- 在单独的协程中执行流
    println("Done 222")
}