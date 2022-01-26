@file:OfficialSupplement

package com.ssy.chapter6

import com.ssy.OfficialSupplement

// 解构声明 P31

data class MyResult(val result: String, val status: Int)

/*
    也可以使用标准类 Pair 并让 function() 返回 Pair<String, Int>，但最好还是使用自己定义的数据类
 */
fun myMethod(): MyResult {
    return MyResult("success", 1)
}

fun myMethod2(): Pair<String, Int> {
    return Pair("success", 2)
}

fun main() {
    val (result, status) = myMethod()
    println(result)
    println(status)

    println("-----")
    val (result2, status2) = myMethod2()
    println(result2)
    println(status2)

    /*
        不需要某个变量的话，可以使用_跳过，并且对应的componentN函数也不会被执行。
     */
    val (_, status3) = myMethod()
    println(status3)

    /*
        map能解构的原因：
        1. 提供 iterator() 函数将map呈现为一系列值（a sequence of value）。
        2. 通过提供函数 component1() 和 component2() 将每个元素呈现为一对（Pair）。
        标准库中提供了如下扩展函数：
        operator fun <K, V> Map<K, V>.iterator(): Iterator<Map.Entry<K, V>> = entrySet().iterator()
        operator fun <K, V> Map.Entry<K, V>.component1() = getKey()
        operator fun <K, V> Map.Entry<K, V>.component2() = getValue()

        lambda表达式使用解构函数
        { a -> ... } // one parameter
        { a, b -> ... } // two parameters
        { (a, b) -> ... } // a destructured pair
        { (a, b), c -> ... } // a destructured pair and another parameter
     */
    val map = mapOf("a" to "aa", "b" to "bb", "c" to "cc")
    for ((key: String, value: String) in map) {
        println("key:$key, value:$value")
    }
    println("-----")

    map.mapValues { entry ->
        "${entry.value} hello"
    }.forEach {
        println(it)
    }

    println("-----")
    map.mapValues { (key, value) ->
        "$value world"
    }.forEach { println(it) }
    println("-----")

    map.mapValues { (_, value) ->
        "$value welcome"
    }.forEach {
        println(it)
    }
    println("-----")

    // kotlin允许我们为解构声明整体指定类型，也可以为每一个具体的component指定类型
    map.mapValues { (_, value): Map.Entry<String, String> ->
        "$value person"
    }.forEach {
        println(it)
    }
    println("-----")

    map.mapValues { (_, value: String) ->
        "$value people"
    }.forEach {
        println(it)
    }


}