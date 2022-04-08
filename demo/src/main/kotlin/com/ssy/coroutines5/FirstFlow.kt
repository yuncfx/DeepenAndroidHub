package com.ssy.coroutines5

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 如果返回List<String>结果类型，那么就只能一次性返回所有值。要想能够表示可以异步计算的流式的值，我们就可以
 * 使用Flow<String>的类型，它非常类似于Sequence<String>类型，但flow是异步计算的。
 *
 * 1. Flow构建器是通过flow函数来实现的，flow函数的参数是一个挂起的函数类型
 * 2. 因此位于flow{}构建器中的代码是可以挂起的。
 * 3. myMethod()方法无需再使用suspend标识符，值是通过emit函数发射出来的。
 * 4. Flow里面的值是通过collect方法来收集的。
 */
private fun myFirstFlow(): Flow<Int> = flow {
    for (i in 1..4) {
        delay(100)
        // sleep是block函数， 一调用之后，也就失去了挂起的作用
//        Thread.sleep(100)
        emit(i)
    }
}

fun main()= runBlocking<Unit> {
    launch {
        for (i in 1..4) {
            println("hello $i")
            delay(200)
        }
    }

    myFirstFlow().collect {
        println(it)
    }
}