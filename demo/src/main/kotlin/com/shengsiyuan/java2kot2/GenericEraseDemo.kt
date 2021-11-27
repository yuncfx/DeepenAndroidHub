package com.shengsiyuan.java2kot2

fun List<String>.myFilter(): List<String> {
    return listOf("hello", "world")
}

@JvmName("myFilter2")
fun List<Int>.myFilter(): List<Int> {
    return listOf(1, 2)
}

fun main(args: Array<String>) {
    val list  = listOf<String>()
    list.myFilter()

    val list2 = listOf<Int>()
    list2.myFilter()
}


