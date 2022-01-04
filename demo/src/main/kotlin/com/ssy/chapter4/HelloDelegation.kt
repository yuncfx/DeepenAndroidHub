package com.ssy.chapter4

// P22 委托 (delegation)
// 类委托
interface MyInterface {
    val message: String
    fun myPrint()
    fun myPrint2()
}

class MyInterfaceImpl(val str: String) : MyInterface {
    override val message = "MyInterfaceImpl: str = $str"
    override fun myPrint() {
        println("welcome $str")
    }

    override fun myPrint2() {
        println(message)
    }
}

/**
 * 委托的原理:
 * by关键字后面的对象实际上会被存储在类的内部，编译器则会将父接口中的所有方法实现出来，
 * 并且将实现转移给委托对象去进行。
 */
class MyClass(myInterface: MyInterface) : MyInterface by myInterface {
    // 如果自己也实现了该方法，那么优先使用自己的方法
    override fun myPrint2() {
        println("hello MyClass")
    }

    override val message: String = "Message of MyClass"
}

fun main(args: Array<String>) {
    val myInterfaceImpl = MyInterfaceImpl("zhangsan")
    val myClass = MyClass(myInterfaceImpl)
    // 以这种方式覆盖的成员（这里指message）不会从委托对象的成员中调用，委托对象只能访问其自己的接口成员实现：
    myClass.myPrint()  // welcome zhangsan
    myClass.myPrint2() // hello MyClass
    println(myClass.message) //Message of MyClass
}

