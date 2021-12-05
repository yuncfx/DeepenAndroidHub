package com.shengsiyuan.reflection

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.functions
import kotlin.reflect.full.memberProperties

/*
    P46
 */
class MyReflection4 {

    var name: String = "hello world"
    var authorName: String = "tony"

    fun printSomething(name: String) {
        println("something:$name")
    }

    fun printSomething(name: String, address: String) {
        println("something:$name, $address")
    }

    fun printNothing() {
        println("nothing")
    }
}

fun main(args: Array<String>) {
    val myReflection4 = MyReflection4::class
    val reflection4 = MyReflection4()

    val functionToInvoke = myReflection4.functions.find { it.name == "printNothing" }
    functionToInvoke?.call(reflection4)

    val functionToInvoke2 = myReflection4.functions.find { it.name == "printSomething" }
    functionToInvoke2?.call(reflection4, "hello")

    val functionToInvoke3 =
        myReflection4.functions.find { it.name == "printSomething" && it.parameters.size > 2 }
    functionToInvoke3?.call(reflection4, "helloWorld", "beijing")

    val variableToInvoke = myReflection4.memberProperties.find { it.name == "name" }
    println(variableToInvoke?.get(reflection4))
    println(variableToInvoke?.call(reflection4))
    println("-----")

    var variableToInvoke2 = myReflection4.memberProperties.find { it.name == "name" }
    println(variableToInvoke2?.get(reflection4))
    if (variableToInvoke2 is KMutableProperty<*>) {
        variableToInvoke2.setter.call(reflection4, "welcome")
    }
    println(variableToInvoke2?.get(reflection4))
}