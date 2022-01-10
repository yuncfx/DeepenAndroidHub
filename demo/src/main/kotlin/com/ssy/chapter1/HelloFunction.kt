package com.ssy.chapter1

import java.lang.NumberFormatException

fun main(args: Array<String>) {
    println(convert2Int("23"))
    println(convert2Int("ab"))

    printMultiply("2", "3")
    printMultiply("2", "a")

    printMultiply2("2", "3")
    printMultiply2("2", "a")

    println(convert2Uppercase("hello world"))
    println(convert2Uppercase(23))
}

fun convert2Int(str: String): Int? {
    return try {
        str.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}

fun printMultiply(a: String, b: String) {
    val a2Int = convert2Int(a)
    val b2Int = convert2Int(b)

    if (null != a2Int && null != b2Int) {
        println(a2Int * b2Int)
    } else {
        println("param not int")
    }
}

fun printMultiply2(a: String, b: String) {
    val a2Int = convert2Int(a)
    val b2Int = convert2Int(b)

    when {
        (null == a2Int) || (null == b2Int) -> {
            println("param not int")
        }
        else -> {
            println(a2Int * b2Int)
        }
    }
}

fun convert2Uppercase(str: Any): String? {
    if (str is String) {
        return str.toUpperCase()
    }
    return null
}