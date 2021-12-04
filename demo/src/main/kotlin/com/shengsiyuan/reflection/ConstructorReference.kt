package com.shengsiyuan.reflection

/*
    构造方法引用 （Constructor Reference)
    要求有两点：
    1. 函数对象的参数要与构造方法的参数保持一致（体现在参数个数与参数类型）
    2. 函数对象的返回结果要与构造方法所在类的类型保持一致
 */
class B(val x: Int)

fun myMethod(factory: (x: Int) -> B) {
    val b: B = factory(3)
    println(b.x)
}

fun main(args: Array<String>) {
    myMethod(::B)
}