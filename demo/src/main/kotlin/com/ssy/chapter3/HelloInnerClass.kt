package com.ssy.chapter3

/**
 * P19
 * 内部类 (Inner Class)
 *
 * 嵌套类与内部类之间的区别和联系：
 * 1. 嵌套类：对应于Java中的静态内部类（即有static修饰的内部类）
 * 2. 内部类：对应于Java中的非静态内部类（没有static修饰的内部类），使用inner关键字在一个类的内部定义的
 * 另外一个类就叫做内部类。
 *
 */
class OuterClass2 {
    private var str: String = "hello world"

    inner class InnerClass {
        fun innerMethod() = this@OuterClass2.str
    }

    // 局部嵌套类
    fun getName(): String {
        class LocalNestedClass {
            val name: String = "localNestedClass"
        }
        val local = LocalNestedClass()
        return local.name
    }
}

fun main(args: Array<String>) {
    println(OuterClass2().InnerClass().innerMethod())

    println(OuterClass2().getName())
}