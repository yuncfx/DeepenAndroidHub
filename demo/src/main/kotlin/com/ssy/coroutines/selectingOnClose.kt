package com.ssy.coroutines

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/**
 * First of all, select is biased to the first clause. When several clauses are selectable at the
 * same time, the first one among them gets selected. Here, both channels are constantly producing
 * strings, so a channel, being the first clause in select, wins. However, because we are using
 * unbuffered channel, the a gets suspended from time to time on its send invocation and gives a
 * chance for b to send, too.
 *
 * The second observation, is that onReceiveCatching gets immediately selected when the channel
 * is already closed.
 */
suspend fun selectAorB(a: ReceiveChannel<String>, b: ReceiveChannel<String>): String {
    return select<String> {
        a.onReceiveCatching { it ->
            val value = it.getOrNull()
            if (value != null) {
                "a -> '$value'"
            } else {
                "Channel 'a' is closed"
            }
        }

        b.onReceiveCatching { it ->
            val value = it.getOrNull()
            if (value != null) {
                "a -> '$value'"
            } else {
                "Channel 'b' is closed"
            }
        }
    }
}

fun main(args: Array<String>) = runBlocking {
    val a = produce<String> {
        repeat(4) { send("Hello $it") }
    }

    val b = produce<String> {
        repeat(4) { send("World $it") }
    }

    repeat(8) {
        println(selectAorB(a, b))
    }

    coroutineContext.cancelChildren()
}