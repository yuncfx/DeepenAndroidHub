package com.ssy.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import java.util.*

// todo don't understand
fun CoroutineScope.stringAsync(time: Int) = async {
    delay(time.toLong())
    "Waited for $time ms"
}

fun CoroutineScope.asyncStringsList(): List<Deferred<String>> {
    val random = Random(13)
    return List(12) { stringAsync(random.nextInt(1000)) }
}

fun main() = runBlocking {
    val list: List<Deferred<String>> = asyncStringsList()
    // wait for the first to complete
    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }

    println(result)
    val countActive = list.count { it.isActive }
    println("$countActive coroutines are still active")
}