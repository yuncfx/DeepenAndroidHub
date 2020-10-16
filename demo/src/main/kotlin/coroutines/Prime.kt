package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    var cur = numbersFrom(2)
    repeat(10) {
        val prime = cur.receive()
        println("prime is $prime")
        cur = filter(cur, prime)
    }
    coroutineContext.cancelChildren() // 取消所有的子协程来让主协程结束
}

@ExperimentalCoroutinesApi
fun CoroutineScope.numbersFrom(start: Int) = produce {
    var x = start
    while (true) send(x++) // 从 start 开始过滤整数流
}

@ExperimentalCoroutinesApi
fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
    for (x in numbers) {
        println("x:$x")
        if (x % prime != 0) {
            println("filter $x")
            send(x)
        }
    }
}
