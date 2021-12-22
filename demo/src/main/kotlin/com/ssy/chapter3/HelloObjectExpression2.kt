package com.ssy.chapter3

/**
 * P21
 * 匿名对象只能在局部变量范围内或是被private修饰的成员变量范围内才能被识别出其真正的类型。
 *
 * 如果将匿名对象当作一个public方法的返回类型或是public属性的类型，那么该方法或是属性的真正
 * 类型就是该匿名对象所声明的父类型，如果没有声明任何父类型，那么其类型就是Any；在这种情况下，
 * 匿名对象中所声明的任何成员都是无法访问的。
 */
class MyClass {
    private var myObject = object {
        fun output() {
            println("output invoked")
        }
    }

    fun test() {
        println(myObject.javaClass)
        println(myObject::class.java)
        myObject.output()
    }
}

class MyClass2 {
    private fun method1() = object {
        val str = "hello"
    }

    internal fun method2() = object {
        val str = "world"
    }

    fun test() {
        val str = method1().str
//        val str2 = method2().str // not compile
    }
}

fun main(args: Array<String>) {
    val myClass = MyClass()
    myClass.test()
}