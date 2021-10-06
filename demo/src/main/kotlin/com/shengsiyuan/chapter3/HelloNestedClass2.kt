package com.shengsiyuan.chapter3

/**
 * P20
 */
class OutClass4 {
    class NestedClass4 {
        init {
            println("NestedClass4 init")
        }
    }
}

class OuterClass5 {
    inner class InnerClass5(str: String) {
        init {
            println(str)
        }
    }
}

fun main(args: Array<String>) {
    val nestedClass4: OutClass4.NestedClass4 = OutClass4.NestedClass4()
    println(nestedClass4)

    val innerClass5: OuterClass5.InnerClass5 = OuterClass5().InnerClass5("hello world")
    println(innerClass5)
}