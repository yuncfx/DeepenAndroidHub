package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * 管道是一种一个协程在流中开始生产可能无穷多个元素的模式：
 */
@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val numbers = produceNumbers() // 从 1 开始生成整数
    val squares = square(numbers) // 整数求平方
    repeat(5) {
        println(squares.receive()) // 输出前五个
    }
    println("Done!") // 至此已完成
    coroutineContext.cancelChildren() // 取消子协程
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce {
    var x = 1
    while (true) send(x++) // 从 1 开始的无限的整数流
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}