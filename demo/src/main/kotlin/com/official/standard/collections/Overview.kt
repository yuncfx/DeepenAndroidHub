@file:Official

package com.official.standard.collections

import com.ssy.Official

/*
    在 Kotlin 中，只读集合类型是协变的。 这意味着如果 Rectangle 类继承自 Shape 类，您可以在需要 List<Shape>
    类型的任何地方使用 List<Rectangle> 类型。 换句话说，集合类型与元素类型具有相同的子类型关系。 Map在值类型上
    是协变的，但在键类型上不是。 可变集合不是协变的——这会导致运行时失败。

    The read-only collection types are covariant. This means that, if a Rectangle class inherits
    from Shape, you can use a List<Rectangle> anywhere the List<Shape> is required. In other words,
    the collection types have the same subtyping relationship as the element types. Maps are
    covariant on the value type, but not on the key type.

    In turn, mutable collections aren't covariant; otherwise, this would lead to runtime failures.
    If MutableList<Rectangle> was a subtype of MutableList<Shape>, you could insert other Shape
    inheritors (for example, Circle) into it, thus violating its Rectangle type argument.
 */
open class Shape(val name: String)

class Rectangle(private val rectangleName: String) : Shape(rectangleName)

fun doSthWithShapes(shapes: List<Shape>) {
    println("The shapes are: ${shapes.joinToString { it.name }}")
}

/*
    在 Kotlin 中，List 的默认实现是 ArrayList，您可以将其视为可调整大小的数组。

    Set 的默认实现——LinkedHashSet——保留了元素插入的顺序。 因此，依赖于顺序的函数，例如 first() 或 last()，
    在这些集合上返回可预测的结果。
    另一种实现 - HashSet - 没有说明元素顺序，因此在其上调用此类函数会返回不可预测的结果。 但是，HashSet 需要
    更少的内存来存储相同数量的元素。

    Map 的默认实现——LinkedHashMap——在迭代map时保留元素插入的顺序。 反过来，另一种实现 - HashMap - 没有说明元素顺序。

 */

fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
    // 将满足predicate条件的元素，保存到shortWords中
    this.filterTo(shortWords) { it.length <= maxLength }
    // throwing away the articles
    val articles = setOf("a", "A", "an", "An", "the", "The")
    shortWords -= articles
}

fun main() {
    val rectangles = listOf(Rectangle("rhombus"), Rectangle("parallelepiped"))
    doSthWithShapes(rectangles)

    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords)
}