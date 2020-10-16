package coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

val mutex = Mutex()
var counter5 = 0

/**
 * 还有 withLock 扩展函数，可以方便的替代常用的 mutex.lock(); try { …… } finally { mutex.unlock() } 模式：
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