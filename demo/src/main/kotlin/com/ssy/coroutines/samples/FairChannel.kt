@file:Official("https://github.com/Kotlin/kotlinx.coroutines/issues/111")

package com.ssy.coroutines.samples

import com.ssy.Official
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

data class Ball(var hits: Int)

/**
 * Note that sometimes channels may produce executions that look unfair due to the nature of
 * the executor that is being used. See this issue for details.
 */
fun main() = runBlocking {
    val table = Channel<Ball>() // 一个共享的 table（桌子）
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0)) // 乒乓球
    delay(1000) // 延迟 1 秒钟
    coroutineContext.cancelChildren() // 游戏结束，取消它们
}

suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // 在循环中接收球
        ball.hits++
        println("$name $ball")
        delay(300) // 等待一段时间
        table.send(ball) // 将球发送回去
    }
}