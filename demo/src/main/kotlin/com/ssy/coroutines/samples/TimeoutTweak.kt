package com.ssy.coroutines.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

var acquired = 0

class Resource {
    init {
        acquired++ // Acquire the resource
    }

    fun close() {
        acquired-- // Release the resource
    }
}

fun main(args: Array<String>) {
    runBlocking {
        repeat(100_1000) { // Launch 100K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50)
                    Resource()
                }

                resource.close()
            }
        }
    }

    // Outside of runBlocking all coroutines have completed
    // nearly print not 0
    println(acquired) // Print the number of resources still acquired
}