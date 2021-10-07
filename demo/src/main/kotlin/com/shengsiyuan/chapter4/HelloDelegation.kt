package com.shengsiyuan.chapter4

// P22 委托 (delegation)
// 类委托
interface MyInterface {
    fun myPrint()
}

class MyInterfaceImpl(val str: String) : MyInterface {
    override fun myPrint() {
        println("welcome$str")
    }
}

/**
 * 委托的原理:
 * by关键字后面的对象实际上会被存储在类的内部，编译器则会将父接口中的所有方法实现出来，
 * 并且将实现转移给委托对象去进行。
 */
class MyClass(myInterface: MyInterface) : MyInterface by myInterface {
    // 如果自己也实现了该方法，那么优先使用自己的方法
    override fun myPrint() {
        println("hello world")
    }
}

fun main(args: Array<String>) {
    val myInterfaceImpl = MyInterfaceImpl("zhangsan")
    MyClass(myInterfaceImpl).myPrint()
}

