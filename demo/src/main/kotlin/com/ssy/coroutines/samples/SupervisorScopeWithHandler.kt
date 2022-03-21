package com.ssy.coroutines.samples

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.lang.AssertionError

/**
 * Another crucial difference between regular and supervisor jobs is exception handling. Every
 * child should handle its exceptions by itself via the exception handling mechanism. This
 * difference comes from the fact that child's failure does not propagate to the parent. It means
 * that coroutines launched directly inside the supervisorScope do use the CoroutineExceptionHandler
 * that is installed in their scope in the same way as root coroutines do
 * (see the CoroutineExceptionHandler section for details).
 *
 * 常规作业和主管作业之间的另一个关键区别是异常处理。 每个孩子都应该通过异常处理机制自行处理其异常。
 * 这种差异来自这样一个事实，即孩子的失败不会传播给父母。 这意味着直接在 supervisorScope 内启动的协程确实使用
 * 安装在其范围内的 CoroutineExceptionHandler，其方式与根协程相同
 * （有关详细信息，请参阅 CoroutineExceptionHandler 部分）。
 */
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    supervisorScope {
        val child = launch(handler) {
            println("The child throws an exception")
            throw AssertionError()
        }
        println("The scope is completing")
    }

    println("The scope is completed")
}