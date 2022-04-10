package com.ssy.coroutines5

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.runBlocking

/**
 * 类似于catch运算符，onCompletion只会看到来自于Flow上游的异常，但是看不到Flow下游的异常。
 *
 * onCompletion是中间操作， 是属于collect终止操作的下游
 */
private fun myMethod(): Flow<Int> = (1..10).asFlow()

fun main() = runBlocking {
    myMethod().onCompletion { cause -> println("Flow completed with $cause") }
        .collect { value ->
            check(value <= 1) { " Collected $value" }
            println(value)
        }
}