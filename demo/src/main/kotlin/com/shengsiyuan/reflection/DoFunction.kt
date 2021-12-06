package com.shengsiyuan.reflection

import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredFunctions

/*
    P48
 */
class DoFunction {
    /*
        普通方法，第一个参数始终是object receiver
     */
    fun method(message: String) {
        println("message:$message")
    }

    fun method(message: String, price: Double) {
        println("message:$message, price:$price")
    }
}

fun main(args: Array<String>) {
    val clazz = DoFunction::class
    val instance = clazz.createInstance()

    val funs = clazz.declaredFunctions
    for (f in funs) {
        if (f.parameters.size == 3) {
            f.call(instance, "Ruby", 23.4)
        }

        if (f.parameters.size == 2) {
            f.call(instance, "Flutter")
        }
    }
}