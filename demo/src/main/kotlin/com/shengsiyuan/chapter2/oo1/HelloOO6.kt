package com.shengsiyuan.chapter2.oo1

/**
 *  重写属性
 */
open class MyParent {
    open val name: String = "parent"
}

class MyChild : MyParent() {
    override val name: String = "child"
}

fun main(args: Array<String>) {
    var myChild = MyChild()
    println(myChild.name)

    println("-----")
    val myChild3 = MyChild3()
    myChild3.method()
    println(myChild3.name)
}

// 在构造方法中重载属性
class MyChild2(override val name: String) : MyParent() {

}

// val 可以 override val
// var 可以 override val
// val 无法 override var
// 本质上，val相当于get方法；var相当于get和set方法
open class MyParent3 {
    open fun method() {
        println("parent method")
    }

    open val name: String
        get() = "parent"

    open var t: String = "h"
}

class MyChild3 : MyParent3() {
    override fun method() {
        super.method()
        println("child method")
    }

    override val name: String
        get() = super.name + " and child"

    override var t: String = "h"
}