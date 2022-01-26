package com.ssy.reflection

import kotlin.reflect.KClass
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

class T(val x: Int)

/**
 * P42
 */
open class Parent
class Son : Parent()
class Daughter : Parent()

/*
    函数（方法）引用
    支持重载
    ::multiBy3表示函数类型 (Int) -> Int的值
 */
fun multiBy3(x: Int): Int {
    return 3 * x
}

fun multiBy3(x: String): Int {
    return 10
}

// 或者，您可以通过将方法引用存储在具有明确指定类型的变量中来提供必要的上下文：
val myReference: (Int) -> Int = ::multiBy3
val myReference2: (String) -> Int = ::multiBy3

val myReference3: (String, Int) -> Char = String::get
val myReference4: String.(Int) -> Char = String::get

/*
    函数组合
 */
fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
    return { x -> f(g(x)) }
}

fun isOdd(x: Int) = x % 2 != 0

val x = 1
var y = 1

val String.lastChar: Char
    get() = this[length - 1]

// To get the Kotlin class that corresponds to a Java class, use the .kotlin extension property:
fun getKClass(o: Any): KClass<Any> = o.javaClass.kotlin

class Foo

// 引用构造方法 ::Foo
fun function(factory: () -> Foo) {
    val x: Foo = factory()
}

class Outer {
    inner class Inner
}

fun main(args: Array<String>) {
    val son: Parent = Son()
    val daughter: Parent = Daughter()
    println(son::class) // com.ssy.reflection.Son
    println(son::class.java) // com.ssy.reflection.Son
    println(daughter::class) // com.ssy.reflection.Daughter
    println(daughter::class.java) // com.ssy.reflection.Daughter

    println("-----")

    val values = listOf<Int>(1, 2, 3, 4)
    println(values.map(::multiBy3))

    val values2 = listOf("a", "b", "c", "d")
    println(values2.map(::multiBy3))

    println("-----")

    println(T::x.javaGetter) // public final int com.ssy.reflection.T.getX()
    println(T::x.javaField) // private final int com.ssy.reflection.T.x
    println(T::x.javaClass) // class com.ssy.reflection.ReflectionKt$main$5

    println("-----")

    println(T(10).javaClass) // class com.ssy.reflection.T
    println(T(10).javaClass.kotlin) // class com.ssy.reflection.T

    println("-----")
    println(String.javaClass) // class kotlin.jvm.internal.StringCompanionObject
    println(String.javaClass.kotlin) // class kotlin.String$Companion
    println(String::class.java) // class java.lang.String
    println(String::class) // class kotlin.String

    println("-----")
    // 引用成员方法或扩展方法
    println(String::toString) // fun kotlin.String.toString(): kotlin.String
    val string = String::toString.invoke("this")
    println(string)

    println("-----")

    /*
        即使您使用对扩展函数的引用来初始化变量，推断的函数类型也将没有接收器，但它会有一个接受接收器对象的附加参数。
        要使用带有接收器的函数类型，请显式指定类型：
     */
    val isEmptyStringList: List<String>.() -> Boolean = List<String>::isEmpty


    println("-----")
    fun length(s: String) = s.length
    val oddLength = compose(::isOdd, ::length)
    val strings = listOf("a", "ab", "abc")
    println(strings.filter(oddLength))

    println("-----")

    println(::x.get()) // 1
    println(::x.name) // x
    ::y.set(2)
    println(y) // 2

    val strs = listOf("a", "bc", "def")
    println(strs.map(String::length)) // [1, 2, 3]

    // To access a property that is a member of a class
    println(T::x.get(T(1)))
    println(String::lastChar.get("hello"))

    println("---constructor---")
    function(::Foo)

    println("--- bound function and property references ---")
    // 绑定函数和属性引用
    val numberRegex = "\\d+".toRegex()
    println(numberRegex.matches("29"))
    // 绑定的属性引用，已经有一个receiver，不需要Receiver作为参数
    val isNumber: (CharSequence) -> Boolean = numberRegex::matches
    println(isNumber("29"))
    val stringList = listOf("abc", "124", "a70")
    stringList.filter(isNumber)
    // 未绑定的属性引用，需要Receiver作为参数
    val matches: (Regex, CharSequence) -> Boolean = Regex::matches
    matches.invoke(numberRegex, "29")

    println("--- Bound constructor references ---")
    val boundInnerCtor = Outer()::Inner
}