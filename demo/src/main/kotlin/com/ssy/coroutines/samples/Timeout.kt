@file:OfficialRelative
package com.ssy.coroutines.samples

import com.ssy.OfficialRelative
import kotlinx.coroutines.*
import java.lang.Exception

fun main() = runBlocking {
    try {
        withTimeout(1300L) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
    } catch (ex: Exception) {

    }
}