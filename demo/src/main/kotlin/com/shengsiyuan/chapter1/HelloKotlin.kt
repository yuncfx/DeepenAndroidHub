package com.shengsiyuan.chapter1

fun main(args: Array<String>) {
    println(sum(1, 2))
    println(sum2(2, 3))
    myPrint(3, 4)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun myPrint(a: Int, b: Int): Unit {
    println(a + b)
    println("$a + $b = ${a + b}")
}