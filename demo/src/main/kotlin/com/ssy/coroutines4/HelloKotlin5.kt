package com.ssy.coroutines4

import kotlinx.coroutines.Job
import kotlinx.coroutines.isActive
import kotlinx.coroutines.runBlocking

/**
 * Job的使用方式以及在Context中的具体应用
 *
 * 协程的Job是归属于其上下文Context的一部分，
 * Kotlin为我们提供了一种简洁的手段来通过协程上下文来获取到协程自身的Job对象
 *
 * 我们可以通过coroutineContext[Job]表达式来访问上下文中的Job对象
 */

fun main() = runBlocking<Unit> {
    val job: Job? = coroutineContext[Job] // coroutineContext.get(Job)
    println(job)
    println(coroutineContext.isActive)
    println(coroutineContext[Job]?.isActive)
}
