package com.ssy.coroutines.samples

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

val mutex = Mutex()
var counter5 = 0

/**
 * 还有 withLock 扩展函数，可以方便的替代常用的 mutex.lock(); try { …… } finally { mutex.unlock() } 模式：
 *
 * fine-grained
 *
 * The locking in this example is fine-grained, so it pays the price. However,
 * it is a good choice for some situations where you absolutely must modify some shared state
 * periodically, but there is no natural thread that this state is confined to.
 */
fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        // 用锁保护每次自增
        massiveRun {
            mutex.withLock {
                counter5++
            }
        }
    }
    println("Counter = $counter5")
}