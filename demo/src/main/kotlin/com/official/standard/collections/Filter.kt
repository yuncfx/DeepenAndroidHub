package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = listOf("one", "two", "three", "four")
    val longerThan3 = numbers.filter { it.length > 3 }
    println(longerThan3)

    val numbersMap = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredMap = numbersMap.filter { (key, value) -> key.endsWith("1") && value > 10 }
    println(filteredMap)

    val filteredIdx = numbers.filterIndexed { index, s -> (index != 0) && (s.length < 5) }
    val filteredNot = numbers.filterNot { it.length <= 3 }
    println(filteredIdx)
    println(filteredNot)

    val numbers2 = listOf(null, 1, "two", 3.0, "four")
    println("All String elements in upper case:")
    numbers2.filterIsInstance<String>().forEach {
        println(it.uppercase())
    }

    val numbers3 = listOf(null, "one", "two", null)
    numbers3.filterNotNull().forEach {
        println(it.length)   // length is unavailable for nullable Strings
    }

    println("partition test---")
    val (match, rest) = numbers.partition { it.length > 3 }
    println(match) // [three, four]
    println(rest) // [one, two]

    println(numbers.any { it.endsWith("e") })
    println(numbers.none { it.endsWith("a") })
    println(numbers.all { it.endsWith("e") })

    println(emptyList<Int>().all { it > 5 })   // vacuous truth

    println("any test---")
    val empty = emptyList<String>()
    println(numbers.any()) // true
    println(empty.any()) // false

    println(numbers.none()) // false
    println(empty.none()) // true
}