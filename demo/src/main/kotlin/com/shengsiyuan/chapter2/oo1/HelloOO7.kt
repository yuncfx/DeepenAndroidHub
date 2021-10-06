package com.shengsiyuan.chapter2.oo1

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

fun main(args: Array<String>) {
    val c = C()
    c.method()
}