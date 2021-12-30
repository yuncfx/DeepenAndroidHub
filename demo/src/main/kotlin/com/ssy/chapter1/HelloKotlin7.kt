package com.ssy.chapter1

fun main(args: Array<String>) {
    println(myPrint("hello"))
    println(myPrint("world"))
    println(myPrint("hello world"))
    println(myPrint("other"))

    println(myPrint2("test"))

    val a = 6
    val result = when (a) {
        1 -> {
            println("a=1")
            10
        }
        2 -> {
            println("a=2")
            20
        }
        3, 4, 5 -> {
            println("a=3 or 4 or 5")
            30
        }
        in 6..10 -> {
            println("a in 6..10")
            40
        }
        else -> {
            println("a is other value")
            50
        }
    }

    println(result)
}

fun myPrint(str: String): String {
    return when (str) {
        "hello" -> str.toUpperCase()
        "world" -> str.toUpperCase()
        "hello world" -> str.toUpperCase()
        else -> "other input"
    }
}

fun myPrint2(str: String): String = myPrint(str)