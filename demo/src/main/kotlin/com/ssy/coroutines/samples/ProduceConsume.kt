package com.ssy.coroutines.samples

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ExperimentalCoroutinesApi
fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
}