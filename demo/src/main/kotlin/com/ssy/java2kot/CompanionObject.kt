package com.ssy.java2kot

/**
 * 在Kotlin中，我们可以将具名对象或是伴生对象中定义的函数注解为@JvmStatic,这样编译器既会在响应对象的类中生成静态方法，
 * 也会在对象自身中生成实例方法。
 */
class People {
    companion object {
        var name = "zhangsan"

        /**
           JvmField修饰的字段，Kotlin编译器会把它提升成public，并可以直接通过[People]来访问
           [People.age]
         */
        @JvmField
        var age = 20

        fun test1() {
            println("test1")
        }

        @JvmStatic
        fun test2() {
            println("test2")
        }
    }
}