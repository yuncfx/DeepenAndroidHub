package com.official.standard

import kotlin.random.Random

/*
    let   -> it    -> lambda result   -> extension function
    run   -> this  -> lambda result   -> extension function
    run   -> -     -> lambda result   -> no: called without the context object
    with  -> this  -> lambda result   -> no: takes the context object as an argument
    apply -> this  -> Context object  -> extension function
    also  -> it    -> Context object  -> extension function

    快速简洁使用各scope方法的指南：
    let:
        1. 在一个非空对象上，执行一个lambda表达式
        2. 引入一个表达式作为局部变量
        返回lambda结果
     apply:
        属性配置，返回上下文对象
     run:
        1. 属性配置并计算结果（修改对象，并把对象作为参数的一部分，计算结果）
        2. 在需要表达式的地方运行语句 非扩展run方法
        返回lambda结果
     also:
        额外作用，如额外赋值，额外打印、额外计算等等,
        返回上下文对象
     with:
        对某一个对象做连续操作，类似build模式
        返回lambda结果

     以上几个范围函数的核心区别：
     1. 引用上下文对象的方法
     2. 返回值
 */
fun main(args: Array<String>) {
    val str = "hello"
    // this
    str.run {
        println("The string's length:$length")
    }

    // it
    str.let {
        println(it)
        println("The string's length is :${it.length}")
    }

    // this, apply用于属性配置
    val adam = Person("Adam").apply {
        age = 20
        city = "London"
    }
    println(adam)

    fun getRandomInt(): Int {
        return Random.nextInt(100).also {
            writeToLog("getRandomInt() generated value $it")
        }
    }

    val i = getRandomInt()
    println(i)

    val numbers = mutableListOf("one", "two", "three")
    // this, 更新list，并返回一个计算结果
    val countEndsWithE = numbers.run {
        add("four")
        add("five")
        count {
            it.endsWith("e")
        }
    }
    println("countEndsWithE:$countEndsWithE")

    // 非扩展run方法，在需要表达式的地方运行语句，返回lambda结果
    val hexNumberRegex = run {
        val digits = "0-9"
        val hexDigits = "A-Fa-f"
        val sign = "+-"
        Regex("[$sign]?[$digits$hexDigits]+")
    }
    for (match in hexNumberRegex.findAll("+123 -FFFF !%*& 88 XYZ")) {
        println(match.value)
    }

    // 对一个对象连续操作
    with(numbers) {
        val firstItem = first()
        val lastItem = last()
        add("four")
        println("First item: $firstItem, last item: $lastItem")
        println("size: ${numbers.size}")
    }

    val number = Random.nextInt(100)
    val evenOrNull = number.takeIf { it % 2 == 0 }
    val oddOrNull = number.takeUnless { it % 2 == 0 }
    println("even:$evenOrNull, odd: $oddOrNull")

    displaySubstringPosition("010000011", "11")
    displaySubstringPosition("010000011", "12")
}


fun displaySubstringPosition(input: String, sub: String) {
    input.indexOf(sub).takeIf { it > 0 }?.let {
        println("The substring $sub is found in $input")
        println("Its start position is $it")
    }
}

fun writeToLog(message: String) {
    println("INFO: $message")
}

data class Person(var name: String, var age: Int = 0, var city: String = "")
