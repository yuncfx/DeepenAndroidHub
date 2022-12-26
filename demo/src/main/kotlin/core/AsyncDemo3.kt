package core

import com.ssy.coroutines5.logX
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

  logX("this in runBlocking $this")

  launch {
    logX("this in launch $this")
  }

  async {
    logX("this in async $this")
  }

  coroutineScope {
    logX("this in coroutineScope $this")
  }

  logX("end process")
}