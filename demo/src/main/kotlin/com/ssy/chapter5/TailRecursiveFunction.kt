package com.ssy.chapter5

import kotlin.math.abs
import kotlin.math.cos

const val eps = 1E-10 //"good enough", could be 10^-15

/*
    尾递归：
        对于一些通常会使用循环的算法，您可以使用递归函数来代替，而不会有堆栈溢出的风险。
        当一个函数被标记为tailrec修饰符并满足所需的形式条件时，编译器会优化递归，生成一个快速循环的代码
    使用尾递归的条件：
        如果一个函数符合tailrec修饰符的条件，那么它必须在执行最后一个操作时调用自己。
    You cannot use tail recursion when there is more code after the recursive call, within try/catch/finally blocks, or on open functions.
    什么情况下不能使用尾递归:
        1. 最后一行不是调用方法自身
        2. 在try/catch/finally代码块中 如把findFixPoint(cos(x))放到这三个代码块中，编译器会提示警告
        3. 可被重写的方法 即open修饰的方法
 */
tailrec fun findFixPoint(x: Double = 1.0): Double =
    if (abs(x - cos(x)) < eps) x else findFixPoint(cos(x))

// 这两个findFixPoint()方法是等价的
private fun findFixPoint(): Double {
    var x = 1.0
    while (true) {
        val y = cos(x)
        if (abs(x - y) < eps) return x
        x = cos(x)
    }
}

// tailrec not working, oom may occur because of lambda
// 计算阶乘, 别整幺蛾子，别搞这种乱七八糟的，用了尾递归就别用高阶函数
tailrec fun cpsFact(a: Int, f: (x: Int) -> Int): Int {
    if (a == 0) {
        return f(1)
    }
    return cpsFact(a - 1) { x -> a * f(x) }
}

interface Function {
    fun invoke(x: Long): Long
}

// 计算阶乘, 别整幺蛾子，别搞这种乱七八糟的，用了尾递归就别用高阶函数
tailrec fun cpsFact(a: Int, f: Function): Long {
    if (a == 0) {
        return f.invoke(1)
    }
    return cpsFact(a - 1, object : Function {
        override fun invoke(x: Long): Long {
            return a * f.invoke(x)
        }
    })
}

fun main() {
//    val result = cpsFact(10000) { x -> x }
//    println(result)

    val result2 = cpsFact(a = 50, object : Function {
        override fun invoke(x: Long): Long {
            return x
        }
    })

    println(result2)
}