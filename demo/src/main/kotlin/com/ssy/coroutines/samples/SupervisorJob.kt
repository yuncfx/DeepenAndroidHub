package com.ssy.coroutines.samples

import kotlinx.coroutines.*

/**
 * As we have studied before, cancellation is a bidirectional relationship propagating through the
 * whole hierarchy of coroutines. Let us take a look at the case when unidirectional cancellation
 * is required.
 * A good example of such a requirement is a UI component with the job defined in its scope. If any
 * of the UI's child tasks have failed, it is not always necessary to cancel (effectively kill) the
 * whole UI component, but if the UI component is destroyed (and its job is cancelled), then it is
 * necessary to cancel all child jobs as their results are no longer needed.
 *
 * Another example is a server process that spawns multiple child jobs and needs to supervise their
 * execution, tracking their failures and only restarting the failed ones.
 *
 * 正如我们之前研究过的，取消是在整个协程层次结构中传播的双向关系。 让我们看一下需要单向取消的情况。

 * 这种需求的一个很好的例子是一个 UI 组件，其工作定义在其范围内。 如果 UI 的任何子任务失败，并不总是需要取消
 * （有效地杀死）整个 UI 组件，但如果 UI 组件被销毁（并且它的作业被取消），则需要取消所有子作业 因为不再需要他们
 * 的结果。

 * 另一个例子是一个服务器进程，它产生了多个子作业，需要监督它们的执行，跟踪它们的失败并只重新启动失败的那些。
 */
fun main() = runBlocking {
    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor)) {
        // 启动第一个子作业——这个示例将会忽略它的异常（不要在实践中这么做！）
        val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
            println("The first child is failing")
            throw AssertionError("The first child is cancelled")
        }
        // 启动第二个子作业
        val secondChild = launch {
            firstChild.join()
            // 取消了第一个子作业且没有传播给第二个子作业
            println("The first child is cancelled: ${firstChild.isCancelled}, but the second one is still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                // 但是取消了监督的传播
                println("The second child is cancelled because the supervisor was cancelled")
            }
        }
        // 等待直到第一个子作业失败且执行完成
        firstChild.join()
        println("Cancelling the supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}