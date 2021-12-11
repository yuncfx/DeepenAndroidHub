package com.shengsiyuan.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Job & Deferred
 * Job，任务，封装了协程中需要执行的代码逻辑。Job可以取消并且有简单的生命周期，它有三种状态：
 *
 * State                                      isActive    isCompleted    isCancelled
 * New (optional initial state)               false       false          false
 * Active (default initial state)             true        false          false
 * Completing (optional transient state)      true        false          false
 * Cancelling (optional transient state)      false       false          true
 * Cancelled (final state)                    false       true           true
 * Completed (final state)                    false       true           false
 *
 * Job 完成时是没有返回值的，如果需要返回值的话，应该使用Deferred， 它是Job的子类
 * public interface Deferred<out T> : Job
 */
fun main() = runBlocking {

    /**
     * CoroutineScope.launch函数属于协程构建器Coroutine builders，Kotlin中还有其它几种Builders，
     * 负责创建协程。
     *
     * launch{}是最常用的构建器，不阻塞当前线程，在后台创建一个新协程，也可以指定协程调度器
     *
     * runBlocking{}是创建一个新的协程，同时阻塞当前线程，直到协程结束。这个不应该在协程中使用，
     * 主要是为main函数和测试设计的。
     *
     * withContext{}不会创建新的协程，在指定协程上运行挂起代码块，并挂起该协程直到代码块运行完成
     *
     * async{}可以实现与launch builder一样的效果，在后台创建一个新协程，唯一的区别是它有返回值，它返回的
     * 是Deferred类型。
     */
    val myJob = GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("Hello")
    // 等待myJob执行完成
    myJob.join()
    println("World")
}