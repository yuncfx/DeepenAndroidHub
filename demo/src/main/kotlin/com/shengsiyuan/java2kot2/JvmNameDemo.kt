package com.shengsiyuan.java2kot2

import com.shengsiyuan.chapter4.MyClass

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