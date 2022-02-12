@file:Official
package com.official.standard.collections

import com.ssy.Official
import java.util.*

val numbersSet = setOf("one", "two", "three", "four")
val emptySet = mutableSetOf<String>()


// to会创建Pair对象，因此如果对性能要求严格，不应该采取此方式，而应该使用直接赋值的方式
val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key4" to 1)
val numbersMap2 = mutableMapOf<String, Int>().apply {
    this["one"] = 1
    this["two"] = 2
}

val map = buildMap {
    put("a", 1)
    put("b", 0)
    put("c", 4)
}
val set = buildSet {
    add(1)
    add(10)
}
val list = buildList {
    add("hello")
}

val emptySet2 = emptySet<String>()
val emptyList = emptyList<String>()
val emptyMap = emptyMap<String, Int>()

// 使用初始化方法初始化list
val doubled = List(3) { it * 2 }
val mutableList = MutableList(3) { it * 2 }

val linkedList = LinkedList(listOf("one", "two", "three"))
val presizedSet = HashSet<Int>(32)
val hashMap = HashMap<String, Int>(16)

val numbers = listOf("one", "two", "three", "four")
val longThan3 = numbers.filter { it.length > 3 }
val numbersSet2 = setOf(1, 2, 3)

fun main(args: Array<String>) {
    println(numbersSet2.map { it * 3 }) // [3, 6, 9]
    println(numbersSet2.mapIndexed { idx, value -> value * idx }) // [0, 2, 6]

    println(numbers.associateWith { it.length }) // {one=3, two=3, three=5, four=4}
}