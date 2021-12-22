package com.ssy.reflection

/*
    我们可以引用特定对象的一个实例方法
    也可以引用特定对象的属性
 */

fun main(args: Array<String>) {
    val str = "abc"
    val getReference = str::get
    println(getReference(1))

    println("-----")

    val myProp = "test"::length
    println(myProp.get())

    val myProp2 = String::length
    println(myProp2.get("test"))
}