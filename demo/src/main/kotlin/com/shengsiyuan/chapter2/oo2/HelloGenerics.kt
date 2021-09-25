package com.shengsiyuan.chapter2.oo2

// 泛型（generics），表示变量类型的参数化
// 参考kot.relative.MyTest2.java这个类
class MyGeneric<T>(t: T) {
    var variable: T = t
}

fun main(args: Array<String>) {
//    var myGeneric: MyGeneric<String> = MyGeneric<String>("hello world")
//    var myGeneric: MyGeneric<String> = MyGeneric("hello world")
    // 借助于Kotlin的类型推断，泛型不用写
    val myGeneric = MyGeneric("hello world")
    println(myGeneric.variable)

    var myClass = MyClass<String, Number>("abc", 2.2)
    myTest(myClass)
}

// out 只能读取， in只能写入
class MyClass<out T, in M>(t: T, m: M) {
    private var t: T = t
    private var m: M = m

    fun get(): T = this.t

    fun set(m: M) {
        this.m = m
    }
}

fun myTest(myClass: MyClass<String, Number>) {
    var myObject: MyClass<String, Int> = myClass
    println(myObject.get())

    var myObject2: MyClass<Any, Int> = myClass
    println(myObject2.get())
}

// 协变（covariant）与逆变（contravariant） 参考《Effective Java》
// 关于协变与逆变的概念及来源
// List<Object>
// List<String>

//List<String> list = new ArrayList()
//List<Object> list2 = list; // 编译失败
//List<? extends Object> list ...

//interface Collection<E> {
//    void addAll(Collection<? extends E> items)
//}
//
//void copyAll(Collection<Object> to, Collection<String> from) {
//    to.addAll(from)
//}
// 协变
//Collection<String>就是Collection<? extends Object>的子类型， 但不是Collection<Object>的子类型。

//List<? super String>

//如果只从中读取数据，而不往里面写入数据，那么这样的对象叫做生产者；（协变）,协变规定上限
//如果只向里面写入数据，而不从中读取数据，那么这样的对象叫做消费者； （逆变），逆变规定下限

//生产者使用 ? extends E; 消费者使用 ? super E 前者叫协变，后者叫逆变。