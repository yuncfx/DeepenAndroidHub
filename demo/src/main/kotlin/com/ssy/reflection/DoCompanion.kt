package com.ssy.reflection

import kotlin.reflect.full.companionObject
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.functions

class DoCompanion(value: Int) {
    constructor() : this(9)

    companion object {
        fun method() {
            println("hello world")
        }
    }

    fun printSomething() {
        println("something")
    }
}

fun main(args: Array<String>) {
    val clazz = DoCompanion::class
    val companion = clazz.companionObject

    println(companion)
    DoCompanion.method()

    println("-----")

    val obj = clazz.createInstance()
    clazz.functions.find { it.name == "printSomething" }?.call(obj)
}