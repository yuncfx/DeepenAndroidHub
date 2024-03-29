package com.ssy.coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/*
 * 每一个协程构建器（包括runBlocking）都会向其代码块作用域中添加一个CoroutineScope实例，我们可以在
 * 该作用域中启动协程， 而无需显式地将其join到一起， 这是因为外部协程（在下面的示例中就是runBlocking）会
 * 等待该作用域中的所有启动的协程全部完成后才会完成。
 */
fun main() = runBlocking {
    GlobalScope.launch {
        delay(1000)
        println("Kotlin Coroutines")
    }

    println("hello")
    delay(2000)
    println("World")
}