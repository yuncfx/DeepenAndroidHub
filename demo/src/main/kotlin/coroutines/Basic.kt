package coroutines

import bean.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//协程就是切线程；
//挂起就是可以自动切回来的切线程；
//挂起的非阻塞式指的是它能用看起来阻塞的代码写出非阻塞的操作，就这么简单。

fun main() {
    GlobalScope.launch(Dispatchers.Default) {
        // 👇 耗时操作
        val user = suspendingRequestUser()
        logThread("GlobalScope 3333")
        println(user)
    }

    logThread("main ... ")
    Thread.sleep(5000L)
}

private suspend fun suspendingRequestUser() : Person = withContext(Dispatchers.IO) {
    logThread("suspendingRequestUser 1111")
    Thread.sleep(3000L)
    logThread("suspendingRequestUser 2222")
    Person("zhangsan", "Beijing", 14, 1, 123456)
}