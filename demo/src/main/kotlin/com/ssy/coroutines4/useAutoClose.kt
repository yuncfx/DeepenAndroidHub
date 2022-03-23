package com.ssy.coroutines4

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

private fun log(logMessage: String) = println("[${Thread.currentThread().name}] $logMessage")

@OptIn(ObsoleteCoroutinesApi::class)
fun main() {
    newSingleThreadContext("Context1").use { ctx1 ->
        newSingleThreadContext("Context2").use { ctx2 ->
            runBlocking(ctx1) {
                log("Started in Context1")

                withContext(ctx2) {
                    log("Working in Context2")
                }

                log("Back to Context1")
            }
        }
    }
}