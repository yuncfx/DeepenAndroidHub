package com.shengsiyuan.chapter5

/*
  闭包
  kotlin中lambda可以修改外部的变量， 不需要为final
 */
fun main(args: Array<String>) {
    var sum = ""
    val strings = arrayOf("hello", "world", "bye")
    strings.filter { it.length > 3 }.forEach {
        sum += it
    }

    println(sum)
}