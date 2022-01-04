package com.ssy.chapter3

import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

// 对象表达式 object expression
/* P20
 * Java中的匿名内部类在很多场景下都有大量使用
 * Kotlin的对象表达式就是为了解决匿名内部类的一些缺陷而产生的。
 *
 * 1. 匿名内部类是没有名字的类
 * 2. 匿名内部类一定是继承了某个父类或是实现了某个接口
 * 3. Java运行时会将该匿名内部类当作它所实现的接口或是所继承的父类来看待。
 *
 * 对象表达式：
 * object[:若干个父类型，中间用逗号隔开] {}
 *
 * 对象表达式和对象声明之间存在一个重要的语义差异：
    * 对象表达式在使用它们的地方立即执行（并初始化）。
    * 对象声明在第一次访问时被延迟初始化。
    * 当外部类使用类似Java中的静态语法访问伴生对象里的属性或方法时，伴生对象会被初始化。
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

/*
    对象表达式 Object expressions,
    如果想定义一个对象，没有必须要实现的父类（或父接口），可以考虑使用对象表达式
 */
val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    // object expressions extend Any, so `override` is required on `toString()`
    override fun toString() = "$hello $world"
}

/*
    创建一个匿名类对象，该对象继承自某些父类（或实现多个父接口），

    匿名内部对象， 可以访问作用域内的外部变量， 不需要final修饰，并且还可以修改变量的值
 */
val anonymousObj = object: MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) { /*...*/ }

    override fun mouseEntered(e: MouseEvent) { /*...*/ }
}

open class A(x: Int) {
    public open val y: Int = x
}

interface B {}

/*
    对象继承多个父类型，且传递构造方法的参数
 */
val ab: A = object : A(1), B {
    override val y = 15
}

interface A1 {
    fun funFromA1() {}
}

interface A2 {
    fun funFromA2() {}
    val x: String
}

interface B1 {

}

class C {
    // 当匿名对象用作本地或私有但不是内联声明（函数或属性）的类型时，其所有成员都可以通过此函数或属性访问
    private fun getObject() = object {
        val x: String = "x"
    }

    /*
     * 如果此函数或属性是公有或私有内联，则其实际类型为：
        * Any 如果匿名对象没有声明的超类型
        * 匿名对象的声明超类型，如果只有一个这样的类型
        * 如果有多个声明的超类型，则显式声明的类型
        * 在所有这些情况下，匿名对象中添加的成员都无法访问。 如果在函数或属性的实际类型中声明了被覆盖的成员，则它们是可访问的
     */
    // The return type is Any. x is not accessible
    fun getObject1() = object {
        val x: String = "x"
    }

    // The return type is A; x is not accessible
    fun getObjectA1() = object: A1 {
        override fun funFromA1() {
            super.funFromA1()
        }

        val x: String = "x"
    }

    // The return type is A; x and funFromA2() is accessible
    fun getObjectA2() = object: A2 {
        override val x: String = "x"
        override fun funFromA2() {
            super.funFromA2()
        }
    }

    // The return type is B; funFromA() and x are not accessible
    fun getObjectB1(): B1 = object: A1, B1 {
        override fun funFromA1() {
            super.funFromA1()
        }

        val x: String = "x"
    }

    fun printX() {
        println(getObject().x)
        println(getObject1())
        println(getObjectA1().funFromA1())
        println(getObjectA2().x)
        println(getObjectA2().funFromA2())
        println(getObjectB1())
    }
}

/*
    对象声明不能是局部的（即不能直接嵌套在函数内部），但可以嵌套到其他对象声明或非内部类中。
    Kotlin中的单例模式，保证线程安全，只有第一次访问才会初始化， 不能用作赋值, 单例模式也可以继承父类
 */
object Singleton: MouseAdapter() {
    fun singleFun() {
        println("singleFun")
    }
}

/**
 * 请注意，即使伴生对象的成员在其他语言中看起来像静态成员，但在运行时它们仍然是真实对象的实例成员，并且可以例如实现接口：
 */
class OutClass {
    object NestedSingleton {
        fun nestedSingleFun() {
            println("nestedSingleFun")
        }
    }

    // 伴生对象可以实现接口
    companion object CompanionObj : A1 {
        fun companionFun() {
            println("companionFun")
        }
    }
}

class MyCompanionClass1 {
    companion object Named { }
}

// x指向MyCompanionClass1的伴生对象
val x = MyCompanionClass1

class MyCompanionClass2 {
    // 伴生对象可以实现接口, 继承抽象类
    companion object : A1, A(2) { }
}

// y指向MyCompanionClass2的伴生对象，指定y的类型为A1
val y: A1 = MyCompanionClass2
// y2指向MyCompanionClass2的伴生对象，指定y的类型为A
val y2: A = MyCompanionClass2

fun main(args: Array<String>) {
    val myObject = object : MyInterface {
        override fun myPrint(i: Int) {
            println("i:$i")
        }
    }

    myObject.myPrint(100)
    println("-----")

    // object expression
    val myObject2 = object {
        init {
            println("init")
        }

        var myProperty = "hello world"
        fun myMethod() = "myMethod()"
        // object expressions extend Any, so `override` is required on `toString()`
        override fun toString(): String {
            return super.toString()
        }
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

    println(helloWorld.toString())

    Singleton.singleFun()

    println(OutClass.NestedSingleton.nestedSingleFun())
    println(OutClass.companionFun())


}
