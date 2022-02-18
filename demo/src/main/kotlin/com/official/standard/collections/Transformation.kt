package com.official.standard.collections

fun main(args: Array<String>) {

    val numbers = setOf(1, 2, 3)
    println(numbers.map { it * 3 })
    println(numbers.mapIndexed { idx, value -> value * idx })

    println(numbers.mapNotNull { if (it == 2) null else it * 3 })
    numbers.mapIndexedNotNull { idx, value -> if (idx == 0) null else value * idx }

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    numbersMap.mapKeys { it.key.uppercase() + " " + it.value }
    numbersMap.mapValues { it.value + it.key.length }

    println("zip test---")
    // zip
    val colors = listOf("red", "brown", "grey")
    val animals = listOf("fox", "bear", "wolf")
    println(colors zip animals) // [(red, fox), (brown, bear), (grey, wolf)]

    val twoAnimals = listOf("fox", "bear")
    println(colors.zip(twoAnimals)) // [(red, fox), (brown, bear)]

    println(colors.zip(animals) { color, animal -> "The ${animal.replaceFirstChar { it.uppercase() }} is $color" })

    val numberPairs = listOf("one" to 1, "two" to 2, "three" to 3, "four" to 4)
    println(numberPairs.unzip()) // ([one, two, three, four], [1, 2, 3, 4])

    println("associate test---")
    val arrays = listOf("one", "two", "three", "four")
    println(arrays.associateWith { it.length })
    println(arrays.associateBy { it.first().uppercaseChar() }) // {O=one, T=three, F=four}
    println(
        arrays.associateBy(
            keySelector = { it.first().uppercaseChar() },
            valueTransform = { it.length })
    ) // {O=one, T=three, F=four}
    val names = listOf("Alice Adams", "Brian Brown", "Clara Campbell")
    println(names.associate { name -> parseFullName(name).let { it.lastName to it.firstName } })

    println("flatten test---")
    val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
    println(numberSets.flatten()) // [1, 2, 3, 4, 5, 6, 1, 2]

    val containers = listOf(
        StringContainer(listOf("one", "two", "three")),
        StringContainer(listOf("four", "five", "six")),
        StringContainer(listOf("seven", "eight"))
    )
    // [one, two, three, four, five, six, seven, eight]
    println(containers.flatMap { it.values }) // map & flatten

    println("join test---")
    val numbers2 = listOf("one", "two", "three", "four")
    println(numbers2) // [one, two, three, four]
    println(numbers2.joinToString()) // one, two, three, four

    val listString = StringBuffer("The list of numbers:")
    numbers2.joinTo(listString)
    println(listString) // The list of numbers: one, two, three, four

    println(numbers2.joinToString(separator = " | ", prefix = "start: ", postfix = ": end"))

    val numbers3 = (1..100).toList()
    // 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, <...>
    println(numbers3.joinToString(limit = 10, truncated = "<...>"))

    // Element: ONE, Element: TWO, Element: THREE, Element: FOUR
    numbers2.joinToString { "Element: ${it.uppercase()}" }
}

data class StringContainer(val values: List<String>)
data class FullName(val firstName: String, val lastName: String)

fun parseFullName(fullName: String): FullName {
    val nameParts = fullName.split(" ")
    if (nameParts.size == 2) {
        return FullName(nameParts[0], nameParts[1])
    } else throw Exception("Wrong name format")
}