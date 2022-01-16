package com.ssy.chapter4

import java.io.File

class NetworkServer

/*
    类型别名为现有类型提供替代名称。 如果类型名称太长，您可以引入一个不同的较短名称并使用新名称。
 */
typealias NodeSet = Set<NetworkServer>
typealias FileTable<K> = MutableMap<K, MutableList<File>>
typealias MyHandler = (Int, String, Any) -> Unit

typealias Predicate<T> = (T) -> Boolean

class A {
    inner class Inner
}

class B {
    inner class Inner
}

typealias AInner = A.Inner
typealias BInner = B.Inner

fun foo(p: Predicate<Int>) = p(42)

/**
 * 类型别名不会引入新类型。 它们等价于相应的底层类型。
 * 当您在代码中添加类型别名 Predicate<T> 并使用 Predicate<Int> 时，Kotlin 编译器总是将其扩展为 (Int) -> Boolean。
 * 因此，只要需要通用函数类型，您就可以传递您类型的变量，反之亦然：
 */
fun main(args: Array<String>) {
    val f: (Int) -> Boolean = { it > 0 }
    println(foo(f))

    val p: Predicate<Int> = { it > 0 }
    println(listOf(1, -2).filter(p))
}