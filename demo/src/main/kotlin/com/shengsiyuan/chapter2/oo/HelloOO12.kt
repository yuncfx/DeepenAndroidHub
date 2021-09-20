package com.shengsiyuan.chapter2.oo

// 可见性 visibility
// Kotlin提供了四种可见性修饰符：private, protected, internal, public
// internal vs public
fun method() {}

open class Clazz {
    private val b = 3
    protected open val c = 4
    internal val d = 5
}

class SubClazz : Clazz() {
}

class Clazz2 {}