package com.ssy.coroutines.samples

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * When flow represents the most recent value of a variable or operation (see also the related section on conflation),
 * @see com.ssy.coroutines.samples.simple6
 * it might be needed to perform a computation that depends on the most recent values of the
 * corresponding flows and to recompute it whenever any of the upstream flows emit a value.
 * The corresponding family of operators is called combine.
 */
fun main() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) }
    val strs = flowOf("one", "two", "three").onEach { delay(400) }
    val startTime = System.currentTimeMillis()
    nums.combine(strs) { a, b -> "$a -> $b" }
        .collect { value ->
            println("$value at ${System.currentTimeMillis() - startTime} ms from start")
        }
}