package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = mutableListOf(1, 2, 3, 4)
    numbers.add(5)
    println(numbers)

    val numbers2 = mutableListOf(1, 2, 5, 6)
    numbers2.addAll(arrayOf(7, 8))
    println(numbers2)
    numbers2.addAll(2, setOf(3, 4))
    println(numbers2)

    val numbers3 = mutableListOf("one", "two")
    numbers3 += "three"
    println(numbers3)
    numbers3 += listOf("four", "five")
    println(numbers3)

    val numbers4 = mutableListOf(1, 2, 3, 4, 3)
    numbers4.remove(3)  // removes the first `3`
    println(numbers4)
    numbers4.remove(5) // removes nothing
    println(numbers4)

    val numbers5 = mutableListOf(1, 2, 3, 4)
    println(numbers5)
    numbers5.retainAll { it >= 3 }
    println(numbers5)
    numbers5.clear()
    println(numbers5) //[]

    val numbersSet = mutableSetOf("one", "two", "three", "four")
    numbersSet.removeAll(setOf("one", "two"))
    println(numbersSet) // [three, four]

    println("numbers6 test---")
    val numbers6 = mutableListOf("one", "two", "three", "three", "three", "three", "four")
    numbers6 -= "three"
    println(numbers6)
    // remove all "three"s
    numbers6 -= listOf("three")
    println(numbers6)
    numbers6 -= listOf("four", "five")
    //numbers -= listOf("four")    // does the same as above
    println(numbers6)
}