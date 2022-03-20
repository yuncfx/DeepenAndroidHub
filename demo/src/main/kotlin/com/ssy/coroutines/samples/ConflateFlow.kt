package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.*

/**
 * 当流代表部分操作结果或操作状态更新时，可能没有必要处理每个值，
 * 而是只处理最新的那个。在本示例中，
 * 当收集器处理它们太慢的时候， conflate 操作符可以用于跳过中间值
 */
fun simple6(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(100) // 假装我们异步等待了 100 毫秒
        emit(i) // 发射下一个值
    }
}

//我们看到，虽然第一个数字仍在处理中，但第二个和第三个数字已经产生，因此第二个是 conflated ，只有最新的（第三个）被交付给收集器：
fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        simple6()
            .conflate() // 合并发射项，不对每个值进行处理， 可以丢弃掉中间部分产生太慢的值
            .collect { value ->
                delay(300) // 假装我们花费 300 毫秒来处理它
                println(value)
            }
    }
    println("Collected in $time ms")
}