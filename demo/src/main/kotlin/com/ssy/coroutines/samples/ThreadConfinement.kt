package com.ssy.coroutines.samples

import kotlinx.coroutines.*

@ObsoleteCoroutinesApi
val counterContext = newSingleThreadContext("CounterContext")
var counter3 = 0

/**
 * 这段代码运行非常缓慢，因为它进行了 细粒度 的线程限制。
 * 每个增量操作都得使用 [withContext(counterContext)]
 * 块从多线程 Dispatchers.Default 上下文切换到单线程上下文。
 */
fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            // 将每次自增限制在单线程上下文中
            withContext(counterContext) {
                counter3++
            }
        }
    }
    println("Counter = $counter3")
}