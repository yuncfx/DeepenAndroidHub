package com.ssy.coroutines

import kotlinx.coroutines.*

/**
 * 线程 vs 协程
 * 线程：指操作系统的线程，也称为内核线程
 * 协程：主要指语言实现的协程，运行在内核线程上。
 *      可以让异步代码同步化，降低异步程序的设计复杂度。
 *      可以实现轻量级的并发，提高系统资源的利用率。
 *
 * 协程的描述：
 *  1. 协程就像非常轻量级的线程。
 *  2. 线程是由系统调度的，线程切换或线程阻塞的开销都比较大
 *  3. 协程依赖于线程，但是协程挂起时不需要阻塞（启动该协程的）线程，几乎是无代价的，协程是由开发者控制的。所以协程也像
 *  用户态的线程，非常轻量级，一个线程中可以创建任意个协程。
 *  4. 协程并不绑定在任何线程上，它可以在一个线程挂起，在另一个线程恢复
 *
 * 协程定义：
 * 协程通过将复杂性放入库中来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为
 * 我们解决其异步性。
 * 该库可以将用户代码的相关部分包装为回调、订阅相关事件、在不同线程（甚至不同机器）上调度执行，
 * 而代码则保持如同顺序执行一样简单。
 *
 * 协程的描述；
 * 协程可以简化异步编程，可以顺序地表达程序，协程也提供了一种避免阻塞线程并用更廉价、更可控的操作
 * 替代线程阻塞的方法---协程挂起。
 *
 * 协程重要概念：
 * CoroutineScope, 可以理解为协程本身，包含了CoroutineContext
 *
 * CoroutineContext，协程上下文，是一些元素的集合，主要包含Job和CoroutineDispatcher元素，可以
 * 代表一个协程的场景
 *
 * EmptyCoroutineContext， 表示一个空的协程上下文
 *
 * CoroutineDispatcher, 协程调度器，决定协程所在的线程或线程池。它可指定协程运行于特定的一个线程、
 * 一个线程池或者不指定任何线程（这样协程就会运行于当前线程）
 * coroutine-core中CoroutineDispatcher有三种标准实现：
 *      Dispatchers.Default
 *      Dispatchers.IO
 *      Dispatchers.Main
 *      Dispatchers.Unconfined, Unconfined就是不指定线程
 */
fun main() {

    /*
        launch函数定义如果不指定CoroutineDispatcher或者没有其它的ContinuationInterceptor,默认
        的协程调度器就是Dispatcher.Default, Default是一个协程调度器，其指定的线程为共有的线程池，
        线程数量至少为2， 最大与CPU数相同。
     */
    GlobalScope.launch {
        /**
         * delay是一个特殊的挂起函数，它挂起该协程一段指定的时间。但是不阻塞底层承载它的线程，其它协程仍旧
         * 可以使用。
         */
        delay(1000) // no blocking delay 1 second
        println("Kotlin Coroutines") // print after delay
    }

    println("Hello")
    Thread.sleep(2000)
    println("World")
}

/*
    runBlocking 的名称意味着运行它的线程（在本例中为主线程）在调用期间被阻塞，直到 runBlocking { ... } 中
    的所有协程完成执行。 你会经常看到在应用程序的最顶层使用 runBlocking，而在实际代码中很少见，因为线程是
    昂贵的资源，阻塞它们效率低下，通常是不希望的。

    协程遵循结构化并发原则，这意味着新的协程只能在限定协程生命周期的特定 CoroutineScope 中启动。 上面的例子
    表明 runBlocking 建立了相应的范围。

    在实际应用程序中，您将启动大量协程。 结构化并发确保它们不会丢失并且不会泄漏。 在其所有子协程完成之前，外部
    作用域无法完成。 结构化并发还确保正确报告代码中的任何错误并且永远不会丢失。
 */
fun t() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}

fun t2() = runBlocking {
    launch { doWorld() }
}

fun t3() = runBlocking {
    doWorld()
    println("Done")
}

suspend fun doWorld() = coroutineScope {
    launch {
        delay(1000L)
        println("World")
    }

    launch {
        delay(1000L)
        println("World 2")
    }

    val job = launch {
        delay(1500L)
        println("job")
    }

    println("hello")
    job.join() // wait until child coroutine completes
    println("done")
}

// 协程是轻量级的线程
fun t4() = runBlocking {
    repeat(100_000) {
        launch {
            delay(5000L)
            print(".")
        }
    }
}