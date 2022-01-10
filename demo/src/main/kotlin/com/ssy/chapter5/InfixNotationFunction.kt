package com.ssy.chapter5

/*
    中缀符号 （infix notation）
    函数还可以通过中缀符号形式来调用，需要满足如下3个条件：
    1. 是成员函数或是扩展函数
    2. 拥有单个参数,且没有默认值
    3. 声明时使用infix关键字

    中缀符号优先级
    Infix function calls have lower precedence than arithmetic operators, type casts, and the rangeTo operator. The following expressions are equivalent:

    * 1 shl 2 + 3 is equivalent to 1 shl (2 + 3)
    * 0 until n * 2 is equivalent to 0 until (n * 2)
    * xs union ys as Set<*> is equivalent to xs union (ys as Set<*>)
    * On the other hand, an infix function call's precedence is higher than that of the boolean operators && and ||, is - and in-checks, and some other operators. These expressions are equivalent as well:
    * a && b xor c is equivalent to a && (b xor c)
    * a xor b in c is equivalent to (a xor b) in c
 */

class InfixTest(private var a: Int) {
    infix fun add(b: Int) = this.a + b

    // 请注意中缀函数总是要求同时指定接收者和参数。
    fun test() {
        add(5)
        this add 5
        //add 5 // Incorrect: the receiver must be specified
    }
}

fun main(args: Array<String>) {
    val infixTest = InfixTest(2)

    println(infixTest.add(5))
    println(infixTest add 5)

}