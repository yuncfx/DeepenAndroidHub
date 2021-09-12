package com.shengsiyuan.coroutines5

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow builder（构建器）
 * 1. flow是最经常使用的一种流构建器
 * 2. flowOf构建器可用于定义能够发射固定数量值的流
 * 3. 对于各种集合与序列来说，他们都提供了asFlow()扩展方法来将自身转换为Flow
 *
 * inline, noinline, crossinline
 *
 * non-local returns, 非局部返回：
 * 实际上表示的是在一个方法内部，我们可以在其中通过一个lambda表达式的返回来直接将外层方法返回。
 *
 * crossinline的作用实际上就是表示被标记的lambda表达式是不允许非局部返回的。
 */
fun main() = runBlocking {
    (1..10).asFlow().collect { println(it) }
    println("............")
    listOf("1a", "2b", "3c").asFlow().collect { println(it) }

    flowOf(10, 20, 30, 40, 50).collect { print(" $it") }
}