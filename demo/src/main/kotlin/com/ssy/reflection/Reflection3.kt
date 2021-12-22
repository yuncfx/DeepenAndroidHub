package com.ssy.reflection

/*
    函数（方法）引用
    支持重载
    ::multiBy3表示函数类型 (Int) -> Int的值
 */
fun multiBy3(x: Int): Int {
    return 3 * x
}

fun multiBy3(x: String): Int {
    return 10
}

val myReference: (Int) -> Int = ::multiBy3
val myReference2: (String) -> Int = ::multiBy3

val myReference3: (String, Int) -> Char = String::get
val myReference4: String.(Int) -> Char = String::get

fun main(args: Array<String>) {
    val values = listOf<Int>(1, 2, 3, 4)
    println(values.map(::multiBy3))

    val values2 = listOf("a", "b", "c", "d")
    println(values2.map(::multiBy3))
}