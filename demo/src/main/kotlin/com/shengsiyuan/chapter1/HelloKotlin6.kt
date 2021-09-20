package com.shengsiyuan.chapter1

fun main(args: Array<String>) {
    var array: IntArray = intArrayOf(1, 2, 3, 4, 5)

    for (item: Int in array) {
        println(item)
    }

    println("-----")

    for (i in array.indices) {
        println("array[$i]:${array[i]}")
    }

    println("-----")
    for ((index, value) in array.withIndex()) {
        println("array[$index]: $value")
    }

}