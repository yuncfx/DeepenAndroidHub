package com.ssy.coroutines.samples

/**
 * 闭包 Closure
 */
fun main() {
    Thread(object : Runnable{
        override fun run() {
            println("style 1")
        }
    }) .start()

    Thread({
        println("style 2")
    }) .start()

    Thread {
        println("style 3")
    }.start()
}