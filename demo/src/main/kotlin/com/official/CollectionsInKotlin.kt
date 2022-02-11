@file:Official
package com.official

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

fun main() {
    val rectangles = listOf(Rectangle("rhombus"), Rectangle("parallelepiped"))
    doSthWithShapes(rectangles)
}