package core

import com.ssy.coroutines5.logX
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.CoroutineStart.LAZY
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

fun main() {
//  runBlocking {
//    launch {
//      delay(1000L)
//      println("world! ")
//    }
//
//    println("Hello, ")
//    delay(2000L)
//  }
//
//  runBlocking {
//    val job = launch {
//      search()
//    }
//    println("Hello, ")
//    job.join()
//  }
//
//  runBlocking {
//    val time = measureTimeMillis {
//      val one = searchItemOne()
//      val two = searchItemTwo()
//      println("The items is $one and $two")
//    }
//
//    println("111 Cost time is $time ms")
//  }
//
//  runBlocking {
//    val time = measureTimeMillis {
//      val one = async { searchItemOne() }
//      val two = async { searchItemTwo() }
//
//      println("The items is ${one.await()} and ${two.await()}")
//    }
//
//    println("222 Cost time is $time ms")
//  }
//
//  runBlocking {
//    val time = measureTimeMillis {
//      val one =
//        withContext(Dispatchers.Default) { searchItemOne() }
//      val two =
//        withContext(Dispatchers.Default) { searchItemTwo() }
//
//      println("The items is $one and $two")
//    }
//
//    println("333 Cost time is $time ms")
//  }
//
//  runBlocking {
//    val time = measureTimeMillis {
//      // async???????, ????await?????
//      val one = async { searchItemOne() }.await()
//      val two = async { searchItemTwo() }.await()
//
//      println("The items is $one and $two")
//    }
//
//    println("444 Cost time is $time ms")
//  }
//
//
//  runBlocking {
//    val time = measureTimeMillis {
//      // ??????await,
//      val one = async { searchItemOne() }
//      val two = async { searchItemTwo() }
//
//      val result = awaitAll(one, two)
//      println("The result is $result")
//    }
//
//    println("555 Cost time is $time ms")
//  }
  runBlocking {
    deepTest3()
    }
//  runBlocking {
//    deepTest2()
//  }
}

suspend fun search() {
    delay(1000L)
  println("search World! ")
}

suspend fun searchItemOne(): String {
  logX("searchItemOne")
    delay(1000L)
    return "item-one"
}

suspend fun searchItemTwo(): String {
    delay(1000L)
    return "item-two"
}
suspend fun deepTest() = coroutineScope {
  val time = measureTimeMillis {
    val t = (1..3).map {
//      coroutineScope {
      async { searchItemOne() }
//      }
    }
    t.awaitAll()
  }
  println("deepTest:$time")
}
fun deepTest2() = runBlocking {
  val time = measureTimeMillis {
    val a1 = async(start = LAZY) { searchItemOne() }
    val a2 = async(start = LAZY) { searchItemTwo() }
    val a3 = async(start = LAZY) { searchItemTwo() }
    awaitAll(a1, a2, a3)
  }
  println("deepTest2:$time")
}
suspend fun deepTest3() = coroutineScope {
  val time = measureTimeMillis {
    val a1 = coroutineScope {
      async { searchItemOne() }
    }
    val a2 = coroutineScope {
      async { searchItemOne() }
    }
    awaitAll(a1, a2)
  }
  println("deepTest2:$time")
}