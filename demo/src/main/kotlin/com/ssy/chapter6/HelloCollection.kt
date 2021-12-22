package com.ssy.chapter6

// Kotlin严格区分可变集合与不可变集合
// 要清楚的一点是：区分可变集合的只读视图与实际上真正的不可变集合
fun main(args: Array<String>) {
    val stringList: MutableList<String> = mutableListOf("hello", "world", "hello world")
    val realOnlyView: List<String> = stringList
    println(stringList)

    stringList.add("welcome")
    println("-----")

    val strings: HashSet<String> = hashSetOf("a", "b", "c", "c")
    println(strings.size)
    println("-----")

    // 只读类型是协变的，因为它只用于从集合中获取数据，而不会修改集合中的数据
    val s = listOf("a", "b")
    val s2: List<Any> = s
    println("-----")

    // 快照
    // toList扩展方法只是复制原集合中的元素，所以返回的集合就可以确保不会发生变化
    val items = mutableListOf("a", "b", "c")
    val items2 = items.toList()

    println(items)
    println(items2)
}