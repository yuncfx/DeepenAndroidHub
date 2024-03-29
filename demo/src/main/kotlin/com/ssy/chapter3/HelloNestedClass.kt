package com.ssy.chapter3

/**
 * P19
 * 嵌套类 (Nested Class)
 * 嵌套类不能访问外部类的其它成员， 只能访问另一个嵌套类。
 */

class OuterClass {
    private val str: String = "hello world"

    class NestedClass {
        fun nestedMethod() = "welcome"
    }

    class NestedClass2 {
        val nestedClass = NestedClass()
    }
}

fun main(args: Array<String>) {
    val clazz = OuterClass.NestedClass().nestedMethod()
    println(clazz)
}
