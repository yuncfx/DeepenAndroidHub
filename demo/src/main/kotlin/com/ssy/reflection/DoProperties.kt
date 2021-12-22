package com.ssy.reflection

import kotlin.reflect.KMutableProperty1
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter
import kotlin.reflect.jvm.javaSetter

class DoProperties {
    var name: String = "Flutter"
    val price: Double = 34.5
}

var test = "test"

fun main(args: Array<String>) {
    val clazz = DoProperties::class
    val instance = clazz.createInstance()
    var props = clazz.declaredMemberProperties

    props.forEach {
        when (it.name) {
            "name" -> {
                val kmp = it as KMutableProperty1<DoProperties, String>
                kmp.set(instance, "Hadoop")
                println(it.get(instance))
            }

            "price" -> {
                println(it.get(instance))
            }
        }
    }

    val topProp = ::test
    topProp.set("change after")
    println(topProp.get())
    println(topProp)
    println(topProp.javaField)
    println(topProp.javaGetter)
    println(topProp.javaSetter)

    val name = DoProperties::name
    name.set(instance, "Ruby")
    println(name.get(instance))
    println(name.javaField)
    println(name.javaGetter)
    println(name.javaSetter)

    val price = DoProperties::price
    println(price.get(instance))
    println(price.javaField)
    println(price.javaGetter)
    // no javaSetter
}