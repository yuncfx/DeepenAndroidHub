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

// 将属性委托给另一个属性
/*
一个属性可以将它的 getter 和 setter 委托给另一个属性。 这种委托可用于顶级和类属性（成员和扩展）。 委托属性可以是：
    * 顶级属性
    * 同一类的成员或扩展属性
    * 另一个类的成员或扩展属性

    这可能很有用，例如，当您想以向后兼容的方式重命名属性时：引入新属性，使用 @Deprecated 注释对旧属性进行注释，并委托其实现。
 */
var topLevelInt: Int = 0
class ClassWithDelegate(val anotherClassInt: Int)

class MyDelegateClass(var memberInt: Int, val anotherClassInstance: ClassWithDelegate) {
    var delegatedToMember: Int by this::memberInt
    var delegatedToTopLevel: Int by ::topLevelInt

    val delegatedToAnotherClass: Int by anotherClassInstance::anotherClassInt
}

val MyDelegateClass.extDelegated: Int by ::topLevelInt

class MyDelegateClassDemo {
    var newName: Int = 0
    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    var oldName: Int by this::newName

    fun isValid() : Boolean {
        return true
    }

    fun doSomething() {
        println("print something")
    }
}


// 本地代理属性
fun example(someCondition:Boolean, computeFoo: () -> MyDelegateClassDemo) {
    val memoizedFoo by lazy(computeFoo)

    /*
        The memoizedFoo variable will be computed on first access only.
        If someCondition fails, the variable won't be computed at all.
        memoizedFoo被访问的时候， computeFoo才会被计算
     */
    if (someCondition && memoizedFoo.isValid()) {
        memoizedFoo.doSomething()
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

    val myDelegateClassDemo = MyDelegateClassDemo()
    myDelegateClassDemo.oldName = 42
    println(myDelegateClassDemo.newName) // 42
}