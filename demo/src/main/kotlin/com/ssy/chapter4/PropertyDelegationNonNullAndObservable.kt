package com.ssy.chapter4

import kotlin.properties.Delegates

// P23
// 非空属性

// notNull适用于那些无法在初始化阶段就确定属性值的场合
class MyPerson {
    var address: String by Delegates.notNull<String>()
    lateinit var name: String
}

/* P24
    可观测属性 (Observable)
    Delegates.observable接收两个参数：初始值与修改处理器
    处理器会在我们每次对属性赋值时得到调用（在赋值完成后被调用）
    处理器接收3个参数：被赋值的属性本身，旧的属性值与新的属性值

    Delegates.vetoable的调用时机与Delegates.observable相反，它是对属性赋值之前被调用，
    根据Delegates.vetoable的返回结果是true还是false，来决定是否真正对属性进行赋值（true就赋值，false就不赋值）。
 */
class Person {
    var age: Int by Delegates.observable(20) { property, oldValue, newValue ->
        println("${property.name}, oldValue:$oldValue, newValue:$newValue")
    }
}

class Person2 {
    var age: Int by Delegates.vetoable(20) {
        property, oldValue, newValue ->  when {
            oldValue <= newValue -> true
            else -> false
        }
    }
}

fun main() {
    val myPerson = MyPerson()
    myPerson.address = "Beijing"
    println(myPerson.address)
    println("-----")

    val person = Person()
    person.age = 30
    person.age = 40
    println("-----")

    val person2 = Person2()
    println(person2.age)
    person2.age = 40
    println(person2.age)

    println("-----")
    person2.age = 30
    println(person2.age)
}