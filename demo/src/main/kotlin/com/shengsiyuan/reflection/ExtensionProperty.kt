package com.shengsiyuan.reflection


/*
    扩展属性
 */
val String.firstChar: Char
    get() = if (this.isEmpty()) throw StringIndexOutOfBoundsException() else this[0]

fun main(args: Array<String>) {
    val str = "xyz"
    println(String::firstChar.get(str))
}