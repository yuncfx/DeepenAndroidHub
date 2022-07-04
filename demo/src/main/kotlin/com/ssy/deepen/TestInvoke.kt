package com.ssy.deepen


fun runInvoke() {
  val run: () -> Unit = {
    println("Run run run")
  }

  fun run2(): () -> Unit = {
    println("Run2 run2 run2")
  }

  Runnable {
    run() // run.invoke()
    run2()() // run2().invoke()
  }.run()
}

class TestObj

class T {
  operator fun invoke() {
    println("t invoke")
  }

  operator fun invoke(t: TestObj) {
    println("t invoke $t")
  }
}

fun main() {
  runInvoke()
  T().invoke()
  T()()
  T()(TestObj())
}