package com.official.standard.collections

fun main(args: Array<String>) {
    val numbersMap = mapOf("one" to 1, "two" to 2, "three" to 3)
    println(numbersMap["one"]) // 1
    println(numbersMap.getOrDefault("four", 10)) // 10
    println(numbersMap.getOrElse("five", { 15 })) // 15
    println(numbersMap["six"]) // null

    println(numbersMap.keys)
    println(numbersMap.values)

    val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
    println(filteredMap)

    val filteredKeysMap = numbersMap.filterKeys { it.endsWith("e") }
    val filteredValuesMap = numbersMap.filterValues { it < 10 }
    println(filteredKeysMap)
    println(filteredValuesMap)

    println(numbersMap + Pair("seven", 7))
    println(numbersMap + Pair("one", 111))
    println(numbersMap + mapOf("eight" to 8, "nine" to 9))

    println(numbersMap - "one")
    println(numbersMap - listOf("two", "four"))

    val numbersMap2 = mutableMapOf("one" to 1, "two" to 2, "three" to 3)
    numbersMap2.putAll(setOf("four" to 4, "five" to 5))
    println(numbersMap2)

    numbersMap2 += mapOf("four" to 4, "five" to 5)

    println(numbersMap2.remove("one"))
    // both key and value will be checked before removing
    numbersMap2.remove("three", 4) // remove nothing
    println(numbersMap2)

    numbersMap2.keys.remove("one")
    numbersMap2.values.remove(3) // remove the first item with value of "3"

    numbersMap2 -= "three"

}