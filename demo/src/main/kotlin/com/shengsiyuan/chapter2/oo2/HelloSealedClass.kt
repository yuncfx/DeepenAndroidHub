package com.shengsiyuan.chapter2.oo2

/*
    密封类 (sealed class)
    密封类是一个抽象的类， 不能直接被实例化
    密封类的子类必须定义在同一个文件中，密封类的子类的子类则不受此限制。
 */
sealed class Calculator

class Add : Calculator()

class Subtract : Calculator()

class Multiply : Calculator()

fun calculate(a: Int, b: Int, cal: Calculator) = when (cal) {
    is Add -> a + b
    is Subtract -> a - b
    is Multiply -> a * b
}

fun main(args: Array<String>) {
    calculate(1, 2, Add())
    calculate(1, 2, Subtract())
    calculate(1, 2, Multiply())
}
