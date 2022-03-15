package com.ssy.coroutines4

import kotlinx.coroutines.*

/**
 * ThreadLocal相关
 */

val threadLocal = ThreadLocal<String>()
fun main() = runBlocking {
    threadLocal.set("hello")
    println("pre main, current thread:${Thread.currentThread()}, thread local value:${threadLocal.get()}")

    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "world")) {
        println("launch start, current thread:${Thread.currentThread()}, thread local value:${threadLocal.get()}")
        yield()
        println("after yield, current thread:${Thread.currentThread()}, thread local value:${threadLocal.get()}")
    }

    job.join()
    println("post main, current thread:${Thread.currentThread()}, thread local value:${threadLocal.get()}")

}