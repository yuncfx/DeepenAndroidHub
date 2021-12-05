package com.shengsiyuan.reflection

import java.io.Serializable
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.superclasses

interface MyInterface

class MyReflection<K, V> {
    var k: K? = null
    var v: V? = null
}

class MySerializable : Serializable, MyInterface

class MyReflection2(var a: String, val flag: Boolean, var age: Int) {

    fun printSomething() {
        println("something")
    }

    fun printNothing() {
        println("")
    }
}

class MyReflection3(value: Int) {
    constructor(amount: Int, color: String) : this(amount) {

    }

    constructor(amount: Int, full: Boolean) : this(amount) {

    }

    fun printSomething() {
        println("something")
    }
}

fun main(args: Array<String>) {
    val myReflection = MyReflection::class

    println(myReflection.typeParameters)
    println(myReflection.typeParameters.size)
    println("first type: " + myReflection.typeParameters[0])
    println("second type: " + myReflection.typeParameters[1])

    println("-----")

    val mySerializable = MySerializable::class
    println(mySerializable.superclasses)
    println(mySerializable.supertypes)

    val myReflection2 = MyReflection2::class
    println(myReflection2.memberProperties)
    println(myReflection2.memberFunctions)

    println("-----")

    val myReflection3 = MyReflection3::class
    println(myReflection3.constructors)

}