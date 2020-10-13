package com.test.demo

fun main() {
    val set: HashSet<String?>  = HashSet(16)

    set.add(null)

    for (i in set) {
        println(i)
    }
}