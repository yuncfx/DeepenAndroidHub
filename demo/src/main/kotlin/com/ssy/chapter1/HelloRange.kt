package com.ssy.chapter1

/**
 * 遍历
 */
fun main(args: Array<String>) {
    val a = 5
    val b = 10
    if (a in 2..b) {
        println("in the range")
    }

    if (a !in 2..b) {
        println("out of the range")
    }

    println("-----")
    for (i in 2..10) {
        println(i)
    }

    for (i in 2.rangeTo(10)) {
        println(i)
    }

    println("-----")

    for (i in 2..10 step 2) {
        println(i)
    }

    println("-----")

    for (z in 4..1) {
        println("no print")
    }

    for (z in 1 until 6) {
        println(a)
    }

    for (z in 4 downTo 1) {
        println(a)
    }

    for (i in 10 downTo 2 step 4) {
        println(i)
    }

    val i = 4
    if (i in 1..5) {
        println(i)
    }
}