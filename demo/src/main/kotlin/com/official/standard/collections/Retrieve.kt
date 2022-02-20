package com.official.standard.collections

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
}