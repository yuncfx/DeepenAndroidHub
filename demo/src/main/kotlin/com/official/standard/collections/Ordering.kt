package com.official.standard.collections

import com.ssy.reflection.length
import kotlin.random.Random

/*
要为用户定义的类型定义自然顺序，请将该类型设为 Comparable 的继承者。 这需要实现 compareTo() 函数。 compareTo() 必须将另一个相同类型的对象作为参数，并返回一个整数值，显示哪个对象更大：

正值表示接收对象更大。

负值表明它小于参数。

零表示对象相等。
 */
class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int = when {
        this.major != other.major -> this.major compareTo other.major // compareTo() in the infix form
        this.minor != other.minor -> this.minor compareTo other.minor
        else -> 0
    }
}

fun main(args: Array<String>) {
    println(Version(1, 2) > Version(1, 3)) // false
    println(Version(2, 0) > Version(1, 5)) // true

    val lengthComparator = Comparator { str1: String, str2: String -> str1.length - str2.length }
    listOf("aaa", "bb", "c").sortedWith(lengthComparator) // [c, bb, aaa]

    println(listOf("aaa", "bb", "c").sortedWith(compareBy { it.length })) // [c, bb, aaa]

    val numbers = listOf("one", "two", "three", "four")
    println(numbers.sorted()) // [four, one, three, two]
    println(numbers.sortedDescending()) // [two, three, one, four]

    println(numbers.sortedBy { it.length })
    println(numbers.sortedByDescending { it.last() })

    println(numbers.sortedWith(compareBy { it.length }))

    // reversed() 返回一个包含元素副本的新集合。 所以，如果你稍后更改原始集合，这不会影响之前获得的 reversed() 的结果。
    println(numbers.reversed()) // [four, three, two, one]
    // 返回同一集合实例的反向视图，因此如果原始列表不会更改，它可能比 reversed() 更轻量级和更可取。
    println(numbers.asReversed()) // [four, three, two, one]

    println(numbers.shuffled())
    println(numbers.shuffled(Random(4)))

}