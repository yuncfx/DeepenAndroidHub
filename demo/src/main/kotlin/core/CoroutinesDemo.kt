package core

import invoke.demo.DemoJava
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    GlobalScope.launch {
        delay(1000L)
        println("world!")
    }

    Thread(Runnable {
        Thread.sleep(500L)
        println("real world!")
    }).start()

    println("Hello, ")

    Thread.sleep(2000L)


    GlobalScope.launch(Dispatchers.Main) {
        DemoJava().test()
    }
}