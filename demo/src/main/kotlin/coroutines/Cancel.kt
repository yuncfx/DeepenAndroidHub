package coroutines

import kotlinx.coroutines.*

fun main() = runBlocking {
    logThread("func runBlocking")
    val job = launch {
        logThread("func run launch in runBlocking")
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion

    job.cancel()
    job.cancelAndJoin()
    println("main: Now I can quit.")
}