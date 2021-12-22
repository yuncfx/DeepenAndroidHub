package com.ssy.kot2java

import kot.relative.MyException

fun main() {
    val myException = MyException()
    println("-----")

    // since Kotlin 1.1
    val clazz = MyException()::class.java
    println(clazz)

    println("-----")

    // since the first Kotlin version
    println(MyException().javaClass)
}