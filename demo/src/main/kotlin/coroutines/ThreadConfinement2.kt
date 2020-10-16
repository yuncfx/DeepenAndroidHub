package coroutines

import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

val counterContext2 = newSingleThreadContext("CounterContext")
var counter4 = 0

/**
 * 在实践中，线程限制是在大段代码中执行的，
 * 例如：状态更新类业务逻辑中大部分都是限于单线程中。下面的示例演示了这种情况， 在单线程上下文中运行每个协程。
 */
fun main() = runBlocking {
    // 将一切都限制在单线程上下文中
    withContext(counterContext2) {
        massiveRun {
            counter4++
        }
    }
    println("Counter = $counter4")
}