@file:Official
package com.official

import com.ssy.Official

/**
 * 异步编码技术 Asynchronous programming techniques:
 * 1. Threading
 * 2. Callbacks
 * 3. Futures, promises, and others
 * 4. Reactive Extensions
 * 5. Coroutines
 */

/*
    到目前为止，线程可能是避免应用程序阻塞的最著名的方法, 但是缺点也很明显：
    1. 创建线程是有代价的，线程需要切换上下文
    2. 线程不是无限的，受限于底层操作系统，在服务端，这个可以是主要的性能瓶颈
    3. 线程不是随时可用的，比如JavaScript甚至不支持线程
    4. 线程不简单，debug线程，避免竞争等

fun postItem(item: Item) {
    val token = preparePost()
    val post = submitPost(token, item)
    processPost(post)
}

fun preparePost(): Token {
    // makes a request and consequently blocks the main thread
    return token
}
*/

/*
    使用Callbacks回调，常见问题：
    1. 回调地狱
    2. 错误处理比较复杂
fun postItem(item: Item) {
    preparePostAsync { token ->
        submitPostAsync(token, item) { post ->
            processPost(post)
        }
    }
}

fun preparePostAsync(callback: (Token) -> Unit) {
    // make request and return immediately
    // arrange callback to be invoked later
}

 */

/*
    Futures, promises, and others，常见的问题：
    1. 不同的编码模式，通常是链式调用，常见的循环、错误处理不太可用在这模式下
    2. 不同的API
    3. 指定返回类型
    4. 错误处理较复杂
fun postItem(item: Item) {
    preparePostAsync()
        .thenCompose { token ->
            submitPostAsync(token, item)
        }
        .thenAccept { post ->
            processPost(post)
        }

}

fun preparePostAsync(): Promise<Token> {
    // makes request and returns a promise that is completed later
    return promise
}

 */

/*
    Reactive extensions：
    Rx 背后的想法是转向所谓的可观察流，我们现在将数据视为流（无限量的数据）并且可以观察到这些流。
    实际上，Rx 只是带有一系列扩展的观察者模式，允许我们对数据进行操作。

    在方法上它与 Futures 非常相似，但可以将 Future 视为返回离散元素，而 Rx 返回一个流。 然而，它也引入了一种
    全新的思考我们的编程模型的方式，著名的表述为：“一切都是流，而且是可观察的”

    与 Futures 相比的一个好处是，鉴于它被移植到如此多的平台，通常我们可以找到一致的 API 体验，
    无论我们使用什么，无论是 C#、Java、JavaScript 还是任何其他可以使用 Rx 的语言。

    此外，Rx 确实引入了一种更好的错误处理方法。
 */

/*
    Coroutines协程：
    1. 除了添加suspend关键字，函数签名保持完全相同，并且返回类型是我们想要返回的类型。
    2. 代码仍然像我们在编写自上而下的同步代码一样编写，不需要任何特殊语法，除了使用一个名为 launch 的函数，它本质上启动了协程（在其他教程中介绍）。
    3. 编程模式和 API 保持不变。 我们可以继续使用循环、异常处理等，无需学习一整套新的 API。
    4. 平台独立，无论我们是针对 JVM、JavaScript 还是任何其他平台，我们编写的代码都是一样的。 在幕后，编译器负责使其适应每个平台。
fun postItem(item: Item) {
    launch {
        val token = preparePost()
        val post = submitPost(token, item)
        processPost(post)
    }
}

suspend fun preparePost(): Token {
    // makes a request and suspends the coroutine
    return suspendCoroutine {  }
}
 */