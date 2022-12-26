package com.ssy.coroutines.samples

//fun simple(): List<Int> = listOf(1, 2, 3)
//
//fun main() {
//    simple().forEach { value -> println(value) }
//}

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * A builder function for Flow type is called flow.
 * Code inside the flow { ... } builder block can suspend.
 * The simple function is no longer marked with suspend modifier.
 * Values are emitted from the flow using emit function.
 * Values are collected from the flow using collect function.
 */
fun simple(): Flow<Int> = flow { // flow builder
    logThread("simple")
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        logThread("launch")
        for (k in 1..3) {
            println("I'm not blocked $k")
            delay(100)
        }
    }
    // Collect the flow
    simple().collect { value -> println(value) }

    /**
     * This is a key reason the simple function (which returns a flow) is not marked with suspend modifier.
     * By itself, simple() call returns quickly and does not wait for anything.
     * The flow starts every time it is collected, that is why we see "Flow started" when we call collect again.
     */
    println("Calling simple function...")
    val flow = simple()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }

    (1..3).asFlow().onStart {
        println("onStart")
    }.onCompletion {
        println("onCompletion")
    }.collect { value -> println(value) }

    flowOf(1, 2, 3).collect { value -> println(value) }
}