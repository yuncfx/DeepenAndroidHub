package com.shengsiyuan.coroutines4

import kotlinx.coroutines.*
import java.util.concurrent.Executors

/**
 * 协程与线程之间的关系
 * 协程上下文与分发器（Coroutine Context与Dispatcher）
 * 协程总是会在某个上下文中执行， 这个上下文实际上是由CoroutineContext类型的一个
 * 实例来表述的，该实例是由Kotlin标准库定义的。
 *
 * 协程上下文本质上是各个元素所构成的一个集合。其中主要的元素包括协程的Job以及分发器。
 *
 * 所谓分发器，其主要功能就是确定由哪个线程来执行我们所指定的协程代码
 *
 * 协程上下文包含了一个协程分发器（CoroutineDispatcher），协程分发器确定了到底由
 * 哪个线程或指示线程池来执行我们所指定的协程。 协程分发器可以将协程的执行限定到一个
 * 具体指定的线程，也可以将其分发到一个线程池中，由线程池中的某个线程来执行。还可以
 * 不加任何限制地执行协程（在这种情况下， 协程到底是由哪个线程或线程池来执行是不确定
 * 的，它需要根据程序的实际执行情况方能确定，这种方式的协程分发器在开发中使用较少，它
 * 只用在一些极为特殊的情况）。
 *
 * 所有的协程构建器（coroutine builder）如launch和async都会接收一个可选的CoroutineContext
 * 参数，该参数可用于显式指定新协程所运行的分发器以及其它上下文元素。
 *
 * 程序分析：
 * 1. 当通过launch来启动协程并且不指定协程分发器时，它会继承启动它的那个CoroutineScope的上下文与
 * 分发器，比如该示例中，它会继承runBlocking的上下文，而runBlocking是运行在main线程中的。
 * 2. Dispatchers.Unconfined是一种很特殊的协程分发器， 它在该示例中也是运行在main线程中，但实际
 * 上，其运行机制与不指定协程分发器时是完全不同的。在日常开发中使用较少。
 * 3. Dispatchers.Default是默认的分发器，当协程是通过GlobalScope来启动的时候，它会使用该默认的
 * 分发器来启动协程，它会使用一个后台的共享线程池来运行我们的协程代码。因此，当launch(Dispatchers.Default)
 * 等价于GlobalScope.launch{}
 * 4.Executors.newSingleThreadExecutor().asCoroutineDispatcher()会创建一个单线程池，该线程
 * 池中的线程用来执行我们所指定的协程代码；在实际开发中，使用专门的线程来执行协程代码代价是非常高的，因此在
 * 协程代码执行完毕后， 我们必须要释放相应的资源， 这里就需要使用close方法来关闭相应的协程分发器，从而释放掉
 * 资源；也可以将该协程分发器存储到一个顶层变量中， 以便在程序的其它地方进行复用。
 */
fun main() = runBlocking<Unit> {
    launch {
        println("No params, thread:${Thread.currentThread().name}")
    }

    launch(Dispatchers.Unconfined) {
        println("Unconfined, thread:${Thread.currentThread().name}")
        delay(100)
    }

    launch(Dispatchers.Default){
        println("Default, thread:${Thread.currentThread().name}")
    }

    launch(Dispatchers.IO) {
        println("IO, thread:${Thread.currentThread().name}")
    }

    val thread = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    launch(thread){
        println("Single thread executor, thread:${Thread.currentThread().name}")
        thread.close()
    }

    GlobalScope.launch {
        println("GlobalScope launch, thread:${Thread.currentThread().name}")
    }
}