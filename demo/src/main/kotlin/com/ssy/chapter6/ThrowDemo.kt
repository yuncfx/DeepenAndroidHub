package com.ssy.chapter6

import java.lang.IllegalArgumentException

// throw在Kotlin中是一个表达式，这样我们可以将throw作为Elvis表达式的一部分。
// throw表达式的类型是一个特殊的类型：Nothing
// 在代码中，可以使用Nothing来标记永远不会返回的函数
fun main(args: Array<String>) {
    val str: String? = "a"
    val str2 = str ?: throw IllegalArgumentException("值不能为空")

    println(str2)
    println("-----")
    val str3 = str ?: myMethod("hello")
    println(str3)

    println("-----")

    val s = null
    println(s is Nothing?)

    val s2 = listOf(null)
    println(s2 is List<Nothing?>)
}

fun myMethod(msg: String): Nothing {
    throw IllegalArgumentException(msg)
}