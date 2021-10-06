package com.shengsiyuan.chapter3

import coroutines.somethingUsefulOneAsync
import java.net.http.WebSocket

// 对象表达式 object expression
/* P20
 * Java中的匿名内部类在很多场景下都有大量使用
 * Kotlin的对象表达式就是为了解决匿名内部类的一些缺陷而产生的。
 */
/*
 * 1. 匿名内部类是没有名字的类
 * 2. 匿名内部类一定是继承了某个父类或是实现了某个接口
 * 3. Java运行时会将该匿名内部类当作它所实现的接口或是所继承的父类来看待。
 */

/*
 * 对象表达式：
 * object[:若干个父类型，中间用逗号隔开] {}
 *
 */
interface MyInterface {
    fun myPrint(i: Int)
}

abstract class MyAbstractClass {
    abstract val age: Int
    abstract fun printInfo()
}

abstract class MyAbstractClass2 {
    abstract val age: Int
    abstract fun printInfo()
}

fun main(args: Array<String>) {
    val myObject = object : MyInterface {
        override fun myPrint(i: Int) {
            println("i:$i")
        }
    }

    myObject.myPrint(100)
    println("-----")

    val myObject2 = object {
        init {
            println("init")
        }

        var myProperty = "hello world"
        fun myMethod() = "myMethod()"
    }

    println(myObject2.myProperty)
    println(myObject2.myMethod())

    println("-----")

    // 只能有一个父类，有多个接口。
    val myObject3 = object: MyInterface, MyAbstractClass() {
        override fun myPrint(i: Int) {
            println("i:$i")
        }

        override val age: Int
            get() = 30

        override fun printInfo() {
            println("printInfo invoked")
        }
    }

    myObject3.myPrint(200)
    println(myObject3.age)
    myObject3.printInfo()
}
