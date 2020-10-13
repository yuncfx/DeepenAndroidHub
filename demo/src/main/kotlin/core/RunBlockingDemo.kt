package core

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    runBlocking {
        launch {
            delay(1000L)
            println("world! ")
        }

        println("Hello, ")
        delay(2000L)
    }

    runBlocking {
        val job = launch {
            search()
        }
        println("Hello, ")
        job.join()
    }

    runBlocking {
        val time = measureTimeMillis {
            val one = searchItemOne()
            val two = searchItemTwo()
            println("The items is $one and $two")
        }

        println("111 Cost time is $time ms")
    }

    runBlocking {
        val time = measureTimeMillis {
            val one = async { searchItemOne() }
            val two = async { searchItemTwo() }

            println("The items is ${one.await()} and ${two.await()}")
        }

        println("222 Cost time is $time ms")
    }
}

suspend fun search() {
    delay(1000L)
    println("World! ")
}

suspend fun searchItemOne(): String {
    delay(1000L)
    return "item-one"
}

suspend fun searchItemTwo(): String {
    delay(1000L)
    return "item-two"
}