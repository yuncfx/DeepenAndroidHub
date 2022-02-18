package com.ssy.chapter1

import java.util.function.Consumer

fun main(args: Array<String>) {
    println(sum(1, 2))
    println(sum2(2, 3))
    myPrint(3, 4)

    println((1..2).random())


    val a: Int = 1
    val b = 2

    var c: Int
    c = 3
    c = 4

    var d = 3
    d = 4

    println(d)

    //

    /**
     *
     */

    /**
     * /**
    */
     */

    val x: Int
    val y: Byte = 20
    x = y.toInt()

    println(x)

    println(multiply(3, 4))
    println(multiply(3, 4))

    val m = intArrayOf(1, 2, 3)
//    m = intArrayOf(4, 5)
    m[0] = 4
    for (item in m) {
        println(item)
    }

    //    var x = 10
//    var y = 20
//
//    var max: Int
//    var min: Int
//
//    if (x > y) {
//        max = x
//        min = y
//    } else {
//        max = y
//        min = x
//    }
//
//    println("max = $max, min:$min")

//    var x = 10
//    var y = 20
//
//    var max = if (x > y) x else y
//    var min = if (x > y) y else x
//    println("max = $max, min:$min")


    // ctrl + shit + enter 快速换行格式化，补充大括号
    var max = if (x > y) {
        println("x > y")
        x
    } else {
        println("x <= y")
        y
    }

    var min = if (x > y) {
        println("x > y")
        y
    } else {
        println("x <= y")
        x
    }

    val array = listOf<String>("hello", "world", "hello world", "welcome", "goodbye")
    for (item in array) {
        println(item)
    }

    println("-----")
    when {
        "world" in array -> println("world in collection")
    }
    println("-----")

    array.filter { it.length > 5 }.map {
        it.uppercase()
    }.sorted().forEach {
        println(it)
    }

    val list: List<String> = listOf("hello", "world", "hello world")
    for (str in list) {
        println(str)
    }
    println("---------")
    list.forEach(Consumer { println(it) })
    println("---------")
    list.forEach(System.out::println)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun myPrint(a: Int, b: Int): Unit {
    println(a + b)
    println("$a + $b = ${a + b}")
}