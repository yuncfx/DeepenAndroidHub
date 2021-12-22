package com.ssy.chapter5

/*
 *  内联函数 (inline function)
 *  优点：将函数体直接移动到调用处，减少了函数调用的消耗
 *  缺点：导致字节码文件变大
 */

inline fun myCalculate(a: Int, b: Int) = a + b

fun main(args: Array<String>) {
    println(myCalculate(1, 2))
}