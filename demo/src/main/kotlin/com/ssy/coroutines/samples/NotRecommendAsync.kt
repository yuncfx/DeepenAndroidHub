@file:Official
package com.ssy.coroutines.samples


import com.ssy.Official
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

// 注意，在这个示例中我们在 `main` 函数的右边没有加上 `runBlocking`
/**
 * @see concurrentSum
 */
fun main() {
    val time = measureTimeMillis {
        // 我们可以在协程外面启动异步执行
        /**
         * 为什么不推荐？
         * 如果one和one.await()中间有逻辑错误，one仍然在继续执行，这是不合适的。
         */
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
        // 但是等待结果必须调用其它的挂起或者阻塞
        // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
        runBlocking {
            logThread("")
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}