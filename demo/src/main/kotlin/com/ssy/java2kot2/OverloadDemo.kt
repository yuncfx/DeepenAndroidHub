package com.ssy.java2kot2

/*
    @JvmOverloads解决Kotlin可以有默认值，而Java中没有默认值的问题， 加入该注解后，就主动生成如下重载方法：
    OverloadDemo(int x)
    OverloadDemo(int x, String y)
 */
class OverloadDemo @JvmOverloads constructor(x: Int, y: String = "hello") {
    fun myMethod(a: Int, b: String, c: Int = 2) {
        println("a:%a, b:$b, c:$c")
    }
}