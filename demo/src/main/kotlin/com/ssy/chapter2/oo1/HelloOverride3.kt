package com.ssy.chapter2.oo1

interface A {
    fun method() {
        println("A")
    }
}

open class B {
    open fun method() {
        println("B")
    }
}

// 明确指定到底使用哪个父类中的方法
class C : A, B() {
    override fun method() {
        super<A>.method()
        super<B>.method()
    }
}

open class BaseClass {
    open fun method() {

    }
}

abstract class ChildClass: BaseClass() {
    // 抽象类继承普通类，可以使用抽象方法重写父类中的普通方法
    abstract override fun method()
}

fun main(args: Array<String>) {
    val c = C()
    c.method()
}