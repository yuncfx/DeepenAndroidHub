package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * 当一个协程被其它协程在 CoroutineScope 中启动的时候，
 * 它将通过 CoroutineScope.coroutineContext 来承袭上下文，并且这个新协程的 Job 将会成为父协程作业的子Job。
 * 当一个父协程被取消的时候，所有它的子协程也会被递归的取消。
 *
 * 有两种方式可以取消掉这种Job继承关系
 * 1. 当明确指定一个scope（如GlobalScope） 来启动一个协程时，则新协程的Job没有父Job。 因此它与这个启动的
 *    作用域无关且独立运行。
 * 2. 当传入一个不同的Job()对象到新协程时，同样新协程的Job也不会继承
 */
fun main() = runBlocking<Unit> {
    // 启动一个协程来处理某种传入请求（request）
    val request = launch {
        // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
        GlobalScope.launch {
            println("job0: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job0: I am not affected by cancellation of the request")
        }

        launch(Job()) {
            println("job1: I run in my own Job and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }

        // 另一个则承袭了父协程的上下文
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // 取消请求（request）的执行
    delay(1000) // 延迟一秒钟来看看发生了什么
    println("main: Who has survived request cancellation?")
}