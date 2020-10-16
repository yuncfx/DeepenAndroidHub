package coroutines

import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

@ObsoleteCoroutinesApi
fun main() {
    // 注意，在这个例子中，当我们不再需要某个在 newSingleThreadContext
    // 中创建的线程的时候， 它使用了 Kotlin 标准库中的 use 函数来释放该线程。
    newSingleThreadContext("Ctx1").use { ctx1 ->
        newSingleThreadContext("Ctx2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in ctx1")
                withContext(ctx2) {
                    log("Working in ctx2")
                }
                log("Back to ctx1")
            }
        }
    }
}