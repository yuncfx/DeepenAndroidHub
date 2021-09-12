package com.shengsiyuan.coroutines5

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Flow的组合
 */
fun main() = runBlocking<Unit> {
    val nums = (1..5).asFlow()
    val strs = flowOf("one", "two", "three", "four", "five")

    nums.zip(strs) { a, b ->
        "$a -> $b"
    }.collect {
        println(it)
    }
}