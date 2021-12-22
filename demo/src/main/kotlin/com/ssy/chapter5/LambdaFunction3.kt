package com.ssy.chapter5

/**
 * P29
 */
fun String.filter(predicate: (Char) -> Boolean): String {
    val str = StringBuilder()
    this.forEach {
        if (predicate(it)) {
            str.append(it)
        }
    }

    return str.toString()
}

fun main(args: Array<String>) {
    val str = "abc2def9xy7"
    println(str.filter {
        it.isDigit()
    })

    println(str.filter { it.isLetter() })
}