package com.ssy.coroutines5

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow builder（构建器）
 * 1. flow是最经常使用的一种流构建器
 * 2. flowOf构建器可用于定义能够发射固定数量值的流
 * 3. 对于各种集合与序列来说，他们都提供了asFlow()扩展方法来将自身转换为Flow
 */
fun main() = runBlocking {
    (1..10).asFlow().collect { println(it) }
    println("............")
    listOf("1a", "2b", "3c").asFlow().collect { println(it) }

    flowOf(10, 20, 30, 40, 50).collect { print(" $it") }
}