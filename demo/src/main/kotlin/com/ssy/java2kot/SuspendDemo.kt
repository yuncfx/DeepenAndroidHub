package com.ssy.java2kot

import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import java.util.concurrent.CompletableFuture
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine

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

val block: suspend () -> String = suspend {
  println("Hello!")
  delay(1000L)
  println("World!")
  "Result"
}

fun main() {
  val continuation = object : Continuation<String>() {
    override val context: CoroutineContext
      get() = EmptyCoroutineContext

    override fun resume(value: String) {
      println("Result is: $value")
    }

    override fun resumeWithException(exception: Throwable) {
      println("Result is: $exception")
    }
  }
  block.startCoroutine(continuation)

  val t = block.createCoroutine(continuation)
  t.resume(Unit)
}