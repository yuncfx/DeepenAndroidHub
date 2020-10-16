package coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun simple2(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main() = runBlocking<Unit> {
    println("111111111")
    simple2().forEach { value -> println(value) }
    println("222222222")
}