package com.ssy.coroutines.samples

import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

suspend fun testContext() = coroutineContext

fun main() = runBlocking {
  println(testContext())
}