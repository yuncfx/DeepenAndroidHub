package com.shengsiyuan.reflection

import kotlin.reflect.full.createInstance

/**
 * P47
 */
class DoConstructor(var name: String) {
    var price = 0.0

    constructor() : this("unknown product") {
        this.price = 0.0
    }

    constructor(name: String, price: Double) : this(name) {
        this.price = price
    }

    companion object {
        fun method() {
            println("hello world")
        }
    }
}

fun main(args: Array<String>) {
    val clazz = DoConstructor::class
    val instance = clazz.createInstance()

    println(instance.name)
    println(instance.price)

    val cons = clazz.constructors
    cons.forEach {
        if (it.parameters.size == 2) {
            val instance2 = it.call("python", 34.5)
            println(instance2.name)
            println(instance2.price)
        }
    }
}