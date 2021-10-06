package com.shengsiyuan.chapter2.oo1

// 扩展：extension
class ExtensionTest {
    fun add(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
}

fun ExtensionTest.multiply(a: Int, b: Int) = a * b

fun main(args: Array<String>) {
    val extensionTest = ExtensionTest()
    println(extensionTest.add(1, 2))
    println(extensionTest.subtract(1, 2))
    println(extensionTest.multiply(1, 2))

    println("-----")
    myPrint(AA())
    myPrint(BB()) // print a

    CC().foo()
}

// 扩展函数的解析是静态的。
/*
    1. 扩展本身并不会修改目标类，也就是说它并不会在目标类中插入新的属性或是方法
    2. 扩展函数的解析是静态分发的，而不是动态的，即不支持多态，调用只取决于对象的声明类型
    3. 调用是由对象声明类型所决定的，而不是由对象的实际类型决定的。
 */
open class AA
class BB: AA()
fun AA.a() = "a"
fun BB.a() = "b"
fun myPrint(aa: AA) {
    println(aa.a())
}

class CC {
    // 类内部的方法优先级高于扩展方法
    fun foo() {
        println("member1")
    }
}

fun CC.foo() {
    println("member2")
}

fun Any?.toString(): String {
    if (null == this) {
        return "null"
    }

    return toString()
}