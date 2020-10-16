package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun simple11(): Flow<Int> = (1..3).asFlow()

fun simple12(): Flow<Int> = flow {
    emit(1)
    throw RuntimeException()
}

@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    simple11()
        .onCompletion { println("Done") }
        .collect { value -> println(value) }


    simple12()
        .onCompletion { cause -> if (cause != null) println("Flow completed exceptionally") }
        .catch { cause -> println("Caught exception") }
        .collect { value -> println(value) }
}