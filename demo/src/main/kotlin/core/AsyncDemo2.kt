package core

import com.ssy.coroutines5.logX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

suspend fun deep1(scope: CoroutineScope) {
  val time = measureTimeMillis {
//    val t = (1..3).map {
//      coroutineScope {
//        logX("coroutineScope ${System.currentTimeMillis()}")
//        async { searchOne() }
//      }
//    }
//    t.awaitAll()

    val t1 = coroutineScope {
      logX("coroutineScope ${System.currentTimeMillis()}")
      searchOne()
    }

    val t2 = coroutineScope {
      logX("coroutineScope ${System.currentTimeMillis()}")
      searchOne()
    }

    val t3 = coroutineScope {
      logX("coroutineScope ${System.currentTimeMillis()}")
      searchOne()
    }

  }
  logX("deep1")
  println("deep1:$time")
}

suspend fun deep2() = coroutineScope {
  val time = measureTimeMillis {
    val t = (1..3).map {
      async { searchOne() }
    }
    t.awaitAll()
  }
  logX("deep2")
  println("deep2:$time")
}

private suspend fun searchOne(): String {
  logX("searchOne")
  delay(1000L)
  return "item-one"
}

fun main() = runBlocking(Dispatchers.IO) {
  deep1(this)
//  deep2()
}

