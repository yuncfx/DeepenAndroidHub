package com.ssy.chapter5

/*
    匿名函数
 */

fun main(args: Array<String>) {
    fun(x: Int, y: Int) = x + y

    fun(x: Int, y: Int): Int {
        return x + y
    }

    val strings = arrayOf("hello", "world", "bye")
    strings.filter(fun(item:String): Boolean = item.length > 3).forEach(fun(item) { println(item) })
}