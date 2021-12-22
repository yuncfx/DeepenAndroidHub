package com.ssy.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 线程 vs 协程
 * 线程：指操作系统的线程，也称为内核线程
 * 协程：主要指语言实现的协程，运行在内核线程上。
 *      可以让异步代码同步化，降低异步程序的设计复杂度。
 *      可以实现轻量级的并发，提高系统资源的利用率。
 *
 *
 *
 * 协程的描述：
 * 协程就像非常轻量级的线程。线程是由系统调度的，线程切换或线程阻塞的开销都比较大
 * 而协程依赖于线程，但是协程挂起时不需要阻塞线程，几乎是无代价的，协程是由开发者控制的。
 * 所以协程也像用户态的线程，非常轻量级，一个线程中可以创建任意个协程。
 *
 * 协程定义：
 * 协程通过将复杂性放入库中来简化异步编程。程序的逻辑可以在协程中顺序地表达，而底层库会为
 * 我们解决其异步性。
 * 该库可以将用户代码的相关部分包装为回调、订阅相关时间、在不同线程（甚至不同机器）上调度执行，
 * 而代码则保持如同顺序执行一样简单。
 *
 * 协程的描述；
 * 协程可以简化异步编程，可以顺序地表达程序，协程也提供了一种避免阻塞线程并用更廉价、更可控的操作
 * 替代线程阻塞的方法---协程挂起。
 *
 * 协程重要概念：
 * CoroutineScope, 可以理解为协程本身，包含了CoroutineContext
 *
 * CoroutineContext， 协程上下文，是一些元素的集合，主要包含Job和CoroutineDispatcher元素，可以
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
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")
    Thread.sleep(2000)
    println("World")
}