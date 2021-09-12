package com.shengsiyuan.coroutines5

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.runBlocking

/**
 * 终止操作 （Terminal Operation）
 *
 * Flow的终止操作都是挂起函数，终止操作才会真正开始执行流的收集。
 * 1. toList与toSet
 * 2. 只获取第一个元素
 * 3. reduce 把一系列的值，汇聚成单个的值
 */

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val result = (1..4).asFlow().map {
        it * it
    }.reduce { a, b -> a + b }
    println(result)
}