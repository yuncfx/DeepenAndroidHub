package com.ssy.chapter2.oo1

/**
 * 重写类
 */
open class Fruit {
    open fun name() {
        println("fruit")
    }

    fun expirationDate() {
        println("1 month")
    }
}

class Apple : Fruit() {
    override fun name() {
        println("apple")
    }
}

open class Orange: Fruit() {
    // 重写后的方法，还可以成为final
    final override fun name() {
        println("orange")
    }
}

fun main(args: Array<String>) {
    val apple = Apple()
    apple.name()
    apple.expirationDate()
}