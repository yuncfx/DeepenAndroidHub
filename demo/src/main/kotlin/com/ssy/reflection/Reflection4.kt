package com.ssy.reflection

import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

class T(val x: Int)

fun main(args: Array<String>) {
    println(T::x.javaGetter)
    println(T::x.javaField)
    println(T::x.javaClass)

    println("-----")
    println(T(10).javaClass)
    println(T(10).javaClass.kotlin)

    println("-----")
    // TODO javaClass vs class.java
    println(String.javaClass)
    println(String.javaClass.kotlin)
    println(String::class.java)
}