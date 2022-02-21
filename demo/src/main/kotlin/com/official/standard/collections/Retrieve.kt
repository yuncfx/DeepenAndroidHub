package com.official.standard.collections

import kotlin.random.Random

fun main(args: Array<String>) {
    val numbers = listOf("one", "two", "three", "four", "five", "six")
    println(numbers.slice(1..3)) // [two, three, four]
    println(numbers.slice(0..4 step 2)) // [one, three, five]
    println(numbers.slice(setOf(3, 5, 0))) // [four, six, one]

    println(numbers.take(3)) //[one, two, three]
    println(numbers.takeLast(3)) //[four, five, six]
    println(numbers.drop(1)) //[two, three, four, five, six]
    println(numbers.dropLast(5)) //[one]

    println(numbers.takeWhile { !it.startsWith('f') }) // [one, two, three]
    println(numbers.takeLastWhile { it != "three" }) // [four, five, six]
    println(numbers.dropWhile { it.length == 3 }) // [three, four, five, six]
    println(numbers.dropLastWhile { it.contains('i') }) // [one, two, three, four]

    val numbers2 = listOf("list", "one", "two", "three", "four", "five", "six")
    println(numbers2.dropLastWhile { it.contains('i') }) // [list, one, two, three, four]

    val numbers3 = (0..13).toList()
    // [[0, 1, 2], [3, 4, 5], [6, 7, 8], [9, 10, 11], [12, 13]]
    println(numbers3.chunked(3))
    // [3, 12, 21, 30, 25]
    println(numbers3.chunked(3) { it.sum() }) // `it` is a chunk of the original collection

    val numbers4 = listOf("one", "two", "three", "four", "five")
    println(numbers4.windowed(3)) // [[one, two, three], [two, three, four], [three, four, five]]

    val numbers5 = (1..10).toList()
    numbers5.windowed(
        3,
        step = 2,
        partialWindows = true
    ) // [[1, 2, 3], [3, 4, 5], [5, 6, 7], [7, 8, 9], [9, 10]]
    println(numbers5.windowed(3) { it.sum() }) // [6, 9, 12, 15, 18, 21, 24, 27]

    println(numbers4.zipWithNext()) // [(one, two), (two, three), (three, four), (four, five)]
    println(numbers4.zipWithNext { a, b -> a.length > b.length }) // [false, false, true, false]

    println("set test---")
    val sets = linkedSetOf("one", "two", "three", "four", "five")
    println(sets.elementAt(3))

    val sortedSet = sortedSetOf("one", "two", "three", "four")
    println(sortedSet.elementAt(0))

    println(numbers.first())
    println(numbers.last())

    println(numbers.elementAtOrNull(5))
    numbers.elementAtOrElse(5) { index -> "the value of index $index is undefined" }

    // may throw throw NoSuchElementException
    println(numbers.first { it.length > 3 })
    println(numbers.last { it.startsWith("f") })

    // return null if not exist
    println(numbers.firstOrNull { it.length > 6 })

    println(numbers5.find { it % 2 == 0 })
    println(numbers5.findLast { it % 2 == 0 })

    val list = listOf<Any>(0, "true", false)
    // may throw NoSuchElementException
    val longEnough = list.firstNotNullOf { item -> item.toString().takeIf { it.length >= 4 } }
    println(longEnough)

    // may throw NoSuchElementException
    println(numbers.random())
    println(numbers.random(Random(3)))

    // return null if not exists
    println(numbers.randomOrNull())

    println(numbers.contains("four"))
    println("zero" in numbers)

    println(numbers.containsAll(listOf("four", "two")))
    println(numbers.containsAll(listOf("one", "zero")))

    println(numbers.isEmpty())
    println(numbers.isNotEmpty())


}