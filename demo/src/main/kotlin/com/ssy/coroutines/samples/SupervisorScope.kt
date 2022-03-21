package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * 对于作用域的并发，
 * 可以用 supervisorScope 来替代 coroutineScope 来实现相同的目的。
 * 它只会单向的传播并且当Job自身执行失败的时候将所有子作业全部取消。
 * Job自身也会在所有的子作业执行结束前等待， 就像 coroutineScope 所做的那样。
 */
fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("The child is sleeping")
                    delay(Long.MAX_VALUE)
                } finally {
                    println("The child is cancelled")
                }
            }
            // 使用 yield 来给我们的子作业一个机会来执行打印
            yield()
            println("Throwing an exception from the scope")
            throw AssertionError()
        }
    } catch (e: AssertionError) {
        println("Caught an assertion error")
    }
}