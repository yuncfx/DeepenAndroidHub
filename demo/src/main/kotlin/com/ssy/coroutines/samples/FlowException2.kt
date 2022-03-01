package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple9(): Flow<String> =
    flow {
        for (i in 1..3) {
            println("Emitting $i")
            emit(i) // 发射下一个值
        }
    }
        .map { value ->
            check(value <= 1) { "Crashed on $value" }
            "string $value"
        }

fun main() = runBlocking<Unit> {
    try {
        simple9().collect { value -> println(value) }
    } catch (e: Throwable) {
        println("Caught $e")
    }

    // 发射器可以使用 catch 操作符来保留此异常的透明性并允许封装它的异常处理。
    // catch 操作符的代码块可以分析异常并根据捕获到的异常以不同的方式对其做出反应：
    // 可以使用 throw 重新抛出异常。
    // 可以使用 catch 代码块中的 emit 将异常转换为值发射出去。
    // 可以将异常忽略，或用日志打印，或使用一些其他代码处理它。
    simple9()
        .catch { e -> emit("Caught $e") } // 发射一个异常
        .collect { value -> println(value) }
}
