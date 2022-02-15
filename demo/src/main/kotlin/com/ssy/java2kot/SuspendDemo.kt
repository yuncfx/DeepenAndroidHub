package com.ssy.java2kot

import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture

suspend fun sayHello(): String {
    delay(1000)
    return "Hi there"
}

abstract class Continuation<in T> : kotlin.coroutines.Continuation<T> {
    abstract fun resume(value: T)
    abstract fun resumeWithException(exception: Throwable)
    override fun resumeWith(result: Result<T>) = result.fold(::resume, ::resumeWithException)
}

//  implementation "org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.5.0"
fun doSomethingAsync(): CompletableFuture<String> =
    GlobalScope.future { sayHello() }

interface Listener {
    fun call(string: String)
}

fun t(listener: Listener) {
    CoroutineScope(Dispatchers.IO).launch {
        val t = sayHello()
        listener.call(t)
    }
}