package com.shengsiyuan.chapter2.oo1

// 扩展：extension
class ExtensionTest {
    fun add(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
}

fun ExtensionTest.multiply(a: Int, b: Int) = a * b


open class Base {}

class Derived : Base() {}

/*
Extensions declared as members can be declared as open and overridden in subclasses.
This means that the dispatch of such functions is virtual with regard to the dispatch receiver type,
but static with regard to the extension receiver type.
 */
open class BaseCaller {
    open fun Base.printFunctionInfo() {
        println("Base extension function in BaseCaller")
    }

    open fun Derived.printFunctionInfo() {
        println("Derived extension function in BaseCaller")
    }

    fun call(b: Base) {
        b.printFunctionInfo()   // call the extension function
    }
}

class DerivedCaller : BaseCaller() {
    override fun Base.printFunctionInfo() {
        println("Base extension function in DerivedCaller")
    }

    override fun Derived.printFunctionInfo() {
        println("Derived extension function in DerivedCaller")
    }
}

fun main(args: Array<String>) {
    val extensionTest = ExtensionTest()
    println(extensionTest.add(1, 2))
    println(extensionTest.subtract(1, 2))
    println(extensionTest.multiply(1, 2))

    println("-----")
    myPrint(AA())
    myPrint(BB()) // print a

    CC().foo()

    BaseCaller().call(Base())   // "Base extension function in BaseCaller"
    DerivedCaller().call(Base())  // "Base extension function in DerivedCaller" - dispatch receiver is resolved virtually
    DerivedCaller().call(Derived())  // "Base extension function in DerivedCaller" - extension receiver is resolved statically
}

// 扩展函数的解析是静态的。
/*
    1. 扩展本身并不会修改目标类，也就是说它并不会在目标类中插入新的属性或是方法
    2. 扩展函数的解析是静态分发的，而不是动态的，即不支持多态，调用只取决于对象的声明类型
    3. 调用是由对象声明类型所决定的，而不是由对象的实际类型决定的。
 */
open class AA
class BB : AA()

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