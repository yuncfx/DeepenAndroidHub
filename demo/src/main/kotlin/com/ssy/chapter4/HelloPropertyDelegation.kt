package com.ssy.chapter4

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
 * P23: 属性委托 delegated property
 * 有四种情况在实际开发中比较有用：
 * 1. 延迟属性
 * 2. 可观测属性
 * 3. 非空属性
 * 4. map属性
 */

class MyDelegate {
    /*
        operator关键字是必须的, thisRef必须是委托类本身或其父类，
        返回值必须是委托类本身或其【子类】, property 必须是 KProperty<*> 类型或其超类型。
        被委托属性被取值时，调用此方法
     */
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String =
        "$thisRef, your delegated property name is ${property.name}"
    /*
        operator关键字是必须的, thisRef必须是委托类本身或其父类，
        value必须是委托类本身或其【父类】, property 必须是 KProperty<*> 类型或其超类型。
        被委托属性被赋值时，调用此方法
     */
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef, new value is $value")
    }
}

class MyDelegate2 {

}

// 这两方法可以为扩展方法
operator fun MyDelegate2.getValue(thisRef: Any?, property: KProperty<*>): String =
    "$thisRef, your delegated property name is ${property.name}"
operator fun MyDelegate2.setValue(thisRef: Any?, property: KProperty<*>, value: String) {
    println("$thisRef, new value is $value")
}

class MyPropertyClass {
    var str: String by MyDelegate()
    var str2: String by MyDelegate2()
}

/*
    通过使用 Kotlin 标准库中的 ReadOnlyProperty 和 ReadWriteProperty 接口，您可以将委托创建为匿名对象，而无需创建新类
 */
fun resourceDelegate(): ReadWriteProperty<Any?, Int> =
    object : ReadWriteProperty<Any?, Int> {
        var curValue = 0
        override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
            return curValue
        }

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
            curValue = value
        }
    }

val readOnly: Int by resourceDelegate() // ReadWriteProperty as val
val readWrite: Int by resourceDelegate()

/*
    委托属性的原理1, 委托给另一个对象

    class C {
        var prop: Type by MyDelegate()
    }

    Kotlin 编译器在参数中提供了有关 prop 的所有必要信息：第一个参数 this 指的是外部类 C 的实例，
    而 this::prop 是描述 prop 本身的 KProperty 类型的反射对象。
    // 编译器生成的代码大概如下:
    class C {
        private val prop$delegate = MyDelegate()
        var prop: Type
            get() = prop$delegate.getValue(this, this::prop)
            set(value: Type) = prop$delegate.setValue(this, this::prop, value)
    }

    委托给另一个属性的话，不需要调用getValue和setValue，而是直接返回被委托的属性，以节约内存，
    class C<Type> {
        private var impl: Type = ...
        var prop: Type by ::impl
    }

    // 生成的代码大概如下：
    class C<Type> {
        private var impl: Type = ...

        var prop: Type
            get() = impl
            set(value) {
                impl = value
            }

        fun getProp$delegate(): Type = impl // This method is needed only for reflection
    }
*/


fun main(args: Array<String>) {
    val myPropertyClass = MyPropertyClass()
    myPropertyClass.str = "hello world"
    println(myPropertyClass.str)

    myPropertyClass.str2 = "hello string 2"
    println(myPropertyClass.str2)
}