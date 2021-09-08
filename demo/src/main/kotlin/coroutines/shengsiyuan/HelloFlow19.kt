package coroutines.shengsiyuan

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking

/**
 * Flatten Flow
 * Flow<Flow<Int>> -> Flow<Int>
 */

private fun myMethod(i: Int): Flow<String> = flow {
    emit("$i: First")
    delay(500)
    emit("$i: Second")
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    (1..3).asFlow().onEach {
        delay(100)
    }
}