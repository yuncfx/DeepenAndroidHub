package com.shengsiyuan.chapter5

/**
 * P30
 */
fun main(args: Array<String>) {
    val strings = arrayOf("hello", "world", "helloworlD", "welcome")

    println(strings.filter {
        it.contains("h")
    })

    println("-----")

    println(strings.filter { it.length > 5 })

    strings.filter { it.endsWith("d", true) }.map {
        it.toUpperCase()
    }.forEach { println(it) }

}