package com.ssy.reflection

import kotlin.reflect.KClass

fun main(args: Array<String>) {
    val kotlinLang = "kotlin"
    val kClass: KClass<out String> = kotlinLang::class
    println(kClass)

    println("-----")
    val kClassDataType: KClass<String> = String::class
    println(kClassDataType)

    println("-----")

    val kclass1: KClass<out String> = "kotlin"::class
    val kclass2: KClass<out String> = "java"::class
    val kclass3: KClass<out String> = "ruby"::class

    println(kclass1)
    println(kclass2)
    println(kclass3)
    println(kclass1 === kclass2)

    val kclass4 = Class.forName("java.util.Date").kotlin
    println(kclass4)

    println(kclass4 == Class.forName("java.util.Date"))

}