package com.ssy.coroutines.samples

fun logThread(msg:String): Unit {
    println("$msg running in thread: [${Thread.currentThread().name}]")
}

fun logTime(block : Unit) : Unit {
    println("running on time : ${System.currentTimeMillis()}")
}