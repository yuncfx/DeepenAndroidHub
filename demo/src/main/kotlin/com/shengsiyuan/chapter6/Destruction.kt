package com.shengsiyuan.chapter6

// 解构声明 P31

data class MyResult(val result: String, val status: Int)

fun myMethod() : MyResult {
    return MyResult("success", 1)
}

fun myMethod2(): Pair<String, Int> {
    return Pair("success", 2)
}

fun main() {
    val (result, status) = myMethod()
    println(result)
    println(status)

    println("-----")
    val (result2, status2) = myMethod2()
    println(result2)
    println(status2)
}