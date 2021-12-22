package com.ssy.kot2java

import kot.relative.MyArray

// P37 互通操作-高级

/*
    数组 (Array)

    Kotlin中的数组是不变的（相对于协变与逆变来说的），这一点与Java存在明显的不同。
    这意味着，我们无法将一个Array<String>赋值给Array<Any>，这样就可以防止可能出现的运行期异常。

    Kotlin提供了原生数组类型来避免自动装箱与拆箱带来的成本：IntArray, DoubleArray, CharArray... 跟Array没有关系
 */

fun main(args: Array<String>) {
    val myArray = MyArray()
    val intArray = intArrayOf(1, 2, 3, 4)
    val a = arrayOf(1, 2)

    myArray.myArrayMethod(intArray)
    println("-----")

    // 当编译为JVM字节码时，编译器会优化对于数组的访问，使之不会产生额外的成本。
    val array = arrayOf(1, 2, 3, 4)
    array[0] = array[0] * 2 // 并不会调用set或是get方法

    for (x in array) {
        println(x)
    }
}