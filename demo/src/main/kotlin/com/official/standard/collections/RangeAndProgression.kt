@file:Official

package com.official.standard.collections

import com.ssy.Official
/*
    注意IntRange, LongRange, CharRange分别实现了
     IntProgression, LongProgression, and CharProgression.
 */
fun main() {
    val i = 1
    if (i in 1..4) {
        print(i)
    }

    for (j in (1..4).reversed()) {
        println(j)
    }

    println("IntProgression test---")
    val t: IntRange = (1..4)
    println(t.first)
    println(t.last)
    println(t.step)
}