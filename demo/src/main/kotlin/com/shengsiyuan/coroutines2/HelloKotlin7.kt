package com.shengsiyuan.coroutines2

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * 由withTimeout函数调用所抛出的TimeoutCancellationException异常是CancellationException的子类， 该异常抛出时， 我们
 * 并未在控制台上看到整个异常堆栈信息， 这是因为在取消的协程中， CancellationException被认为是协程的一种正常的完成原因。
 *
 * 不过，我们在该示例中，是在main函数中使用了withTimeout函数。
 *
 * 既然CancellationException仅仅是一个异常而已，所有的资源也都会以通常的方式来关闭，那么我们就可以将相关的代码放到一个try...catch
 * 块中；此外，Kotlin还提供了另外一个更加友好的函数调用:withTimeoutOrNull;从功能角度来看， 它非常类似于withTimeout，不过当超时发生时
 * ，它并不会抛出CancellationException异常，而是会直接返回null。
 *
 * 对于withTimeout函数调用来说， 如果将其放置到try...catch块中，那么调用函数就是下面这样：
 * try {
 *  ...
 * } catch(ex: TimeoutCancellationException) {
 *  ...
 * }
 *
 */

fun main() = runBlocking {
    val result = withTimeoutOrNull(1900) {
        repeat(1000) {
            i-> println("hello, $i")
            delay(300)
        }

        "hello world"
    }

    println("result is $result")
}