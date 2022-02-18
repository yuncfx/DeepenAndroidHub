package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = listOf("one", "two", "three", "four", "one")

    val plusList = numbers + "five"
    val minusList = numbers - listOf("one") // remove all "one"s
    val minusList2 = numbers - "one" // remove the first "one"
    println(plusList) // [one, two, three, four, one, five]
    println(minusList) // [two, three, four]
    println(minusList2) // [two, three, four, one]

}