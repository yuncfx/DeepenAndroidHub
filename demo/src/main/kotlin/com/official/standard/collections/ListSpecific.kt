package com.official.standard.collections

import kotlin.math.sign

data class Product(val name: String, val price: Double)

fun priceComparison(product: Product, price: Double) = sign(product.price - price).toInt()

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 4)
    println(numbers.get(0))
    println(numbers[0])
    //numbers.get(5)                         // exception!
    println(numbers.getOrNull(5))             // null
    println(numbers.getOrElse(5) { it }) // 5

    val numbers1 = (0..13).toMutableList()
    // returns a view of the specified elements range as a list.
    val view = numbers1.subList(3, 6)
    println(view)

    // numbers1.add(5, 14)
    // throw java.util.ConcurrentModificationException
    //println(view)
    numbers1[5] = 55
    println(view)

    val numbers2 = listOf(1, 2, 3, 4, 2, 5)
    println(numbers2.indexOf(2))
    println(numbers2.lastIndexOf(2))
    println(numbers2.indexOfFirst { it > 2 })
    println(numbers2.indexOfLast { it % 2 == 1 })

    val numbers3 = mutableListOf("one", "two", "three", "four")
    numbers3.sort()
    println(numbers3)
    println(numbers3.binarySearch("two"))  // 3
    println(numbers3.binarySearch("z")) // -5
    println(numbers3.binarySearch("two", 0, 2))  // -3

    val productList = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0)
    )

    println(
        productList.binarySearch(
            Product("AppCode", 99.0),
            compareBy<Product> { it.price }.thenBy { it.name })
    )

    val colors = listOf("Blue", "green", "ORANGE", "Red", "yellow")
    println(colors.binarySearch("RED", String.CASE_INSENSITIVE_ORDER))

    val productList2 = listOf(
        Product("WebStorm", 49.0),
        Product("AppCode", 99.0),
        Product("DotTrace", 129.0),
        Product("ReSharper", 149.0)
    )

    println(productList2.binarySearch { priceComparison(it, 99.0) })

    val numbers4 = mutableListOf(1, 2, 3, 4)
    numbers4.fill(3)
    println(numbers4) // [3, 3, 3, 3]

    val numbers5 = mutableListOf("one", "two", "three", "four")

    numbers5.sort()
    numbers5.sorted()
    println("Sort into ascending: $numbers5")
    numbers5.sortDescending()
    numbers5.sortedDescending()
    println("Sort into descending: $numbers5")

    numbers5.sortBy { it.length }
    println("Sort into ascending by length: $numbers5")
    numbers5.sortByDescending { it.last() }
    println("Sort into descending by the last letter: $numbers5")

    numbers5.sortWith(compareBy<String> { it.length }.thenBy { it })
    println("Sort by Comparator: $numbers5")

    numbers5.shuffle()
    println("Shuffle: $numbers5")

    numbers5.reverse()
    println("Reverse: $numbers5")
}