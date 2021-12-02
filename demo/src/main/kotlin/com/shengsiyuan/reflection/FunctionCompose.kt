package com.shengsiyuan.reflection

/*
函数组合
 */

fun <A, B, C> myCompose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun isEven(x: Int) = 0 == x % 2

fun length(s: String) = s.length

fun main(args: Array<String>) {
    val evenLength = myCompose<String, Int, Boolean>(::isEven, ::length)
    val strings = listOf("a", "ab", "abc", "abcd", "abcde")
    println(strings.filter(evenLength))
}