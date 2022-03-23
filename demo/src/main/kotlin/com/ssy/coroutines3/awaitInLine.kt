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
        // 虽然是挂起函数，但是需要分别等待每个函数的执行结果，因此耗时是累加的
        println("$value1 + $value2 = ${value1 + value2}")
    }

    // approximately 5031
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
