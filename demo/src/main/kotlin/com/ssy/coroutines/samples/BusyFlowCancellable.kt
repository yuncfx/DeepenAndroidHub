package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// flow{} 可以正常取消
fun foo(): Flow<Int> = flow {
    for (i in 1..5) {
        println("Emitting $i")
        emit(i)
    }
}

/**
 * 在协程处于繁忙循环的情况下，必须明确检测是否取消。
 * 可以添加 .onEach { currentCoroutineContext().ensureActive() }，
 * 但是这里提供了一个现成的 cancellable 操作符来执行此操作：
 */
fun main() = runBlocking<Unit> {
    (1..5).asFlow().cancellable().collect { value ->
        if (value == 3) cancel()
        println(value)
    }

    // never come here.
    // be cancellable
    println("another cancelling ---")
    foo().collect { value ->
        if (value == 3) cancel()
        println(value)
    }

    // not cancellable until collect return, still print 5
    // asFlow()不能正常取消
    (1..5).asFlow().collect { value ->
        if (value == 3) cancel()
        println(value)
    }
}