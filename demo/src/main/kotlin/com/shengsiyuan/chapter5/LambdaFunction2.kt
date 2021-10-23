package com.shengsiyuan.chapter5

/*
    P29
 */
fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int): Int {
    return calculate(a, b)
}

fun main(args: Array<String>) {
    println(myCalculate(2, 3) { x, y -> x + y })
    println(myCalculate(2, 3) { x, y -> x - y })
    println(myCalculate(2, 3) { x, y -> x * y })
    println(myCalculate(2, 3) { x, y -> x / y })
}