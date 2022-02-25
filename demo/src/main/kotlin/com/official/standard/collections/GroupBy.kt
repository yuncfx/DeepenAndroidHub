package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = listOf("one", "two", "three", "four", "five")

    // {O=[one], T=[two, three], F=[four, five]}
    println(numbers.groupBy { it.first().uppercase() })
    // {o=[ONE], t=[TWO, THREE], f=[FOUR, FIVE]}
    println(numbers.groupBy(keySelector = { it.first() }, valueTransform = { it.uppercase() }))

    println(numbers.groupingBy { it.first() }.eachCount())
}