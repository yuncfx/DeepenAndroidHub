package com.ssy.chapter2.oo1

// 伴生对象也可以支持扩展
class CompanionObjectExtension {
    companion object MyObject {

    }
}

fun CompanionObjectExtension.MyObject.method() {
    println("hello world")
}

fun main(args: Array<String>) {
    CompanionObjectExtension.method()
}