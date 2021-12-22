package com.ssy.java2kot2

class JvmNameDemo {
    val a: Int
        @JvmName("getAValue")
        get() = 20

    fun getA() = 30
}

fun main(args: Array<String>) {
    val jvmNameDemo = JvmNameDemo()
    println(jvmNameDemo.a)
    println(jvmNameDemo.getA())
}