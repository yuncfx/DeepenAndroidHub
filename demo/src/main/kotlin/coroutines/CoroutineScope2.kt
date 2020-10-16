package coroutines

import kotlinx.coroutines.*

/**
 * 常用的 Dispatchers ，有以下三种：

 * Dispatchers.Main：Android 中的主线程
 * Dispatchers.IO：针对磁盘和网络 IO 进行了优化，适合 IO 密集型的任务，比如：读写文件，操作数据库以及网络请求
 * Dispatchers.Default：适合 CPU 密集型的任务，比如计算
 */
class CoroutineScope2 {
    fun testScope() {
        CoroutineScope(Dispatchers.Main).launch {
            logThread("main  .......")
        }
    }
}

fun main() {
    // 方法一通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的。
    runBlocking {
        logThread("runBlocking")
        delay(1000L)
    }

    //方法二和使用 runBlocking 的区别在于不会阻塞线程。但在 Android 开发中同样不推荐这种用法，因为它的生命周期会和 app 一致，且不能取消
    GlobalScope.launch {
        logThread("GlobalScope")
    }

    val coroutineScope = CoroutineScope(Dispatchers.Default);
    coroutineScope.launch {
        logThread("1111111")
    }

    coroutineScope.launch(Dispatchers.IO) {
        logThread("22222222")
    }

//    coroutineScope.launch(Dispatchers.Main) {
//        logThread("33333333")
//    }

//    CoroutineScope(Dispatchers.Main).launch {
//        logThread("ddddddddd")
//    }

    coroutineScope.launch(Dispatchers.Unconfined) {
        logThread("555555555")
    }

    CoroutineScope2().testScope()

//    Thread.sleep(2000L)
}