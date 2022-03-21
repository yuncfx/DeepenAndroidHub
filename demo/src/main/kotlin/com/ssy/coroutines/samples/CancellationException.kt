package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * 取消与异常
 * 取消与异常紧密相关。
 * 协程内部使用 CancellationException 来进行取消，
 * 这个异常会被所有的处理者忽略，所以那些可以被 catch 代码块捕获的异常仅仅应该被用来作为额外调试信息的资源。
 * 当一个协程使用 Job.cancel 取消的时候，它会被终止，但是它不会取消它的父协程。
 *
 * CoroutineExceptionHandler 仅在未捕获的异常（未以任何其他方式处理的异常）上调用。 特别是，所有子协程
 * （在另一个 Job 的上下文中创建的协程）将其异常的处理委托给其父协程，父协程也委托给父协程，依此类推，直到根，
 * 因此永远不会使用安装在其上下文中的 CoroutineExceptionHandler . 除此之外，async builder 总是捕获所有异常
 * 并在生成的 Deferred 对象中表示它们，因此它的 CoroutineExceptionHandler 也不起作用。
 */

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) {
        launch { // 第一个子协程
            try {
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    println("The first child finished its non cancellable block")
                }
            }
        }
        launch { // 第二个子协程
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
    println("never print this line")
}