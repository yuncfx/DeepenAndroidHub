package com.ssy.chapter2.oo2

import org.junit.Assert.assertTrue

// P15 todo 协变， 逆变， 不变
// 在Kotlin中：Consumer in, Producer out
class ParameterizedClass<A>(private val value: A) {
    fun getValue(): A {
        return this.value
    }
}

class ParameterizedProducer<out T>(private val value: T) {
    fun get(): T {
        return this.value
    }
}

class ParameterizedConsumer<in T> {
    fun toString(value: T): String {
        return value.toString()
    }
}

fun main(args: Array<String>) {
    val clazz = ParameterizedClass("hello world")
    val result = clazz.getValue()
    assertTrue(result is String)

    println("-----")
    val parameterizedProducer = ParameterizedProducer("welcome")
    val myRef: ParameterizedProducer<Any> = parameterizedProducer
    assertTrue(myRef is ParameterizedProducer<Any>)

    println("-----")
    val parameterizedConsumer = ParameterizedConsumer<Number>()
    val myRef2: ParameterizedConsumer<Int> = parameterizedConsumer

    // List<Object> list = new ArrayList();
    // List<? super String> list2 = list;
    assertTrue(myRef2 is ParameterizedConsumer<Int>)
}