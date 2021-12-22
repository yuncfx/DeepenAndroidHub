package com.ssy.chapter2.oo1

// 在Kotlin中， 所有类在默认情况下都是无法被继承的， 所有类默认都是final的。
// open的含义与final相反
open class Parent(name: String, age: Int) {

}

class Child(name: String, age: Int) : Parent(name, age) {

}

open class Parent2(name: String) {

}

// 在Kotlin中，如果一个类没有primary构造方法， 那么这个类的每个secondary构造方法需要通过
// super关键字来初始化父类型，或是通过其它secondary构造方法来完成这个继承。
// 不同的secondary构造方法可以调用父类型不同的构造方法
class Child2: Parent2 {
    constructor(name: String) : super(name)
    constructor(name: String, age: Int) : this(name)
}