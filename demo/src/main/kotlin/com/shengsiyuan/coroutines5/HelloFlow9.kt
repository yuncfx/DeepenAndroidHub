package com.shengsiyuan.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * transform 中间转换符
 */
private suspend fun myExecution(input: Int): String {
    delay(1000)
    return "output: $input"
}

fun main() = runBlocking {
    (1..5).asFlow().transform { input ->
        emit("my input:$input")
        emit(myExecution(input))
        emit("hello world")
    }.collect {
        println(it)
    }
}