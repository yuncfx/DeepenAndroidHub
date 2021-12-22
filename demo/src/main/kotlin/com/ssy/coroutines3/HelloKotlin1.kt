package com.ssy.coroutines3

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 挂起函数的组合
 */
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = intValue1()
        val value2 = intValue2()
        println("$value1 + $value2 = ${value1 + value2}")
    }

    println("total time: $elapsedTime")
}

private suspend fun intValue1(): Int {
    delay(2000)
    return 15
}

private suspend fun intValue2(): Int {
    delay(3000)
    return 20
}
