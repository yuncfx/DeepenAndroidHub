package com.shengsiyuan.chapter6

// Range
fun main(args: Array<String>) {
    val i = 4
    if (i in 1..5) {
        println(i)
    }
    println("-----")

    for (a in 1..4) {
        println(a)
    }
    println("-----")

    for (a in 4..1) {
        println("no print")
    }

    println("-----")
    for (a in 4 downTo 1) {
        println(a)
    }

    println("-----")
    for (a in 1..6 step 2) {
        println(a)
    }
    println("-----")
    for (a in 6 downTo 1 step 2) {
        println(a)
    }
    println("-----")
    for (a in 1 until 6) {
        println(a)
    }

}