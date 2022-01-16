package com.ssy.chapter5

/*
    P28, P29
    高阶函数(high-order function)与lambda
    接收一个函数为参数或返回值为一个函数，这个函数就是高阶函数

    Lambda表达式格式要求：
    1. 一个lambda表达式总是被一对花括号所包围
    2. 其参数（如果有的话）位于 -> 之前（参数类型是可以省略的）
    3. 执行体位于 -> 之后

    在Kotlin中，如果一个函数的最后一个参数是个函数，那么可以将lambda表达式作为实参传递进去，
    并且可以在调用时放在方法圆括号外去使用

    TODO 学一下Java8新特性
 */
val multiply: (Int, Int) -> Int = { a, b -> a + b }
val add: (Int, Int) -> Int = { a, b -> a + b }
val subtract = { a: Int, b: Int -> a - b }
val myAction = { println("hello world") }

val myReturnNull: (Int, Int) -> Int? = { _, _ -> null }
val functionMaybeNull: ((Int, Int) -> Int)? = null
fun myCalculate(a: Int, b: Int, calculate: (Int, Int) -> Int): Int {
    return calculate(a, b)
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val str = StringBuilder()
    this.forEach {
        if (predicate(it)) {
            str.append(it)
        }
    }

    return str.toString()
}

fun main() {
    println(myCalculate(2, 3) { x, y -> x + y })
    println(myCalculate(2, 3) { x, y -> x - y })
    println(myCalculate(2, 3) { x, y -> x * y })
    println(myCalculate(2, 3) { x, y -> x / y })

    val str = "abc2def9xy7"
    println(str.filter {
        it.isDigit()
    })

    println(str.filter { it.isLetter() })
}