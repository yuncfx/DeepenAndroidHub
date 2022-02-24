package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = setOf("one", "two", "three")
    println(numbers union setOf("four", "five")) // [one, two, three, four, five]
    println(setOf("four", "five") union numbers) // [four, five, one, two, three]

    println(numbers intersect setOf("two", "one")) // [one, two]
    println(numbers subtract setOf("three", "four")) // [one, two]
    println(numbers subtract setOf("four", "three")) // [one, two]

    val list1 = listOf(1, 1, 2, 3, 5, 8, -1)
    val list2 = listOf(1, 1, 2, 2, 3, 5)
    println(list1 intersect list2) // result on two lists is a Set  [1, 2, 3, 5]
    println(list1 union list2) // equal elements are merged into one [1, 2, 3, 5, 8, -1]
}