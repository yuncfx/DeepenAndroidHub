package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * catch 过渡操作符遵循异常透明性，仅捕获上游异常（catch 操作符上游的异常，但是它下面的不是）。
 * 如果 collect { ... } 块（位于 catch 之下）抛出一个异常，那么异常会逃逸：
 */
fun simple10(): Flow<Int> = flow {
    for (i in 1..3) {
        println("Emitting $i")
        emit(i)
    }
}

fun main() = runBlocking<Unit> {
    simple10()
        .catch { e -> println("Caught $e") } // 不会捕获下游异常
        .collect { value ->
            check(value <= 1) { "Collected $value" }
            println(value)
        }
}