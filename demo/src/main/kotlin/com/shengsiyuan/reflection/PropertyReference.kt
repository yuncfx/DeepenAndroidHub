package com.shengsiyuan.reflection

/*
    属性引用 (Property Reference)
    属性引用的用法与函数（方法）引用的用法完全一致，都是通过::形式来引用

    表达式::a表示类型KProperty<Int>的属性对象，我们可以通过get()来获取其值，也可以通过name属性来获取其名字。

    对于可变属性，比如说var b = 5;  ::b返回的类型是KMutableProperty<Int>的值，它有一个set()方法
 */
const val a = 3
var b = 5
fun main(args: Array<String>) {

    println(::a)
    println(::a.get())
    println(::a.name)

    println("-----")

    ::b.set(10)
    println(b)
    println(::b.get())

    val values = listOf("a", "abc", "abcd")
    val t = String::length
    println(values.map(t))

    val x = MyPropertyDemo::x
    println(x.get(MyPropertyDemo(10)))
}

/*
    要想访问一个类当中的成员属性，需要使用全限定名称
 */
class MyPropertyDemo(val x: Int)