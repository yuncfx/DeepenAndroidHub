package com.ssy.reflection

fun main(args: Array<String>) {
    val c = String::class // KClass
    println(c) //class kotlin.String
    println("-----")

    val c2 = String::class.java
    println(c2) //class java.lang.String
}