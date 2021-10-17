package com.shengsiyuan.chapter5

/*
    P29
 */
fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int): Int {
    return calculate(a, b)
}

