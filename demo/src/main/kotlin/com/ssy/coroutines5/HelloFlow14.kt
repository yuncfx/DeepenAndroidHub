package com.ssy.coroutines5

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private fun myMethod(): Flow<Int> = flow {
    withContext(Dispatchers.Default) {
        for (i in 1..4) {
            Thread.sleep(100)
            emit(i)
        }
    }
}

fun main() = runBlocking {
    myMethod().collect {
        println(it)
    }
}