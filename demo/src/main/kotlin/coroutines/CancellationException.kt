package coroutines

import kotlinx.coroutines.*

/**
 * 取消与异常
 * 取消与异常紧密相关。
 * 协程内部使用 CancellationException 来进行取消，
 * 这个异常会被所有的处理者忽略，所以那些可以被 catch 代码块捕获的异常仅仅应该被用来作为额外调试信息的资源。
 * 当一个协程使用 Job.cancel 取消的时候，它会被终止，但是它不会取消它的父协程。
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
}