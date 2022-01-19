@file:PublicAccount("鱿鱼游戏")
package com.ssy.deepen

import com.ssy.PublicAccount

// public fun hello(): Unit
fun hello() = println("Hello, World")

// public fun hello1(): Unit
fun hello1() = run {
    println("Hello, World")
}

// public fun hello2(): () → Unit
fun hello2() = {
    println("Hello, World")
}

const val world = "multiline world"

/*
    在Java编译器处理之后，else if结构实际上只是用一个"单行"if调用else来处理的，也就是说，不管有多少个else if，
    实际是都会转化为else中的嵌套。【在Kotlin中，函数在if-else块之前被解析，所以.let { print(it) }只适用于最后
    的else if。】所以在这种情况下，第一个if语句的结果将不会被使用，函数将立即返回。为了避免这种情况，你可以将整个
    if ... else ...包裹在小括号中，然后在其上加上.let。

    if (num >= 0) {
        String var1 = num > 0 ? "positive" : "zero";
        boolean var2 = false;
        boolean var3 = false;
        int var5 = false;
        boolean var6 = false;
        System.out.println(var1);
    }
 */
fun printNumberSign(num: Int) {
    if (num < 0) {
        "negative"
    } else if (num > 0) { // else if 实际也是用 if来处理
        "positive"
    } else {
        "zero"
    }.let {
        println(it)
    }
}

// todo， 找出run 和 run2的区别
fun run() {
    val run: () -> Unit = {
        println("Run run run")
    }

    fun run2(): () -> Unit = {
        println("Run2 run2 run2")
    }

    Runnable {
        run() // run.invoke()
        run2()() // run2().invoke()
    }.run()
}

operator fun (() -> Unit).plus(f: () -> Unit): () -> Unit = {
    this()
    f()
}

// public val whatAmI1: () → Unit
val whatAmI1 = {}

// public val whatAmI2: Unit
val whatAmI2 = {}()

fun f1(): Int {
    return return 42
}

fun f2() {
    throw throw Exception()
}

fun f3() {
    var i = 0
    // 变量初始化和类声明都是Kotlin中的语句，它们没有声明任何返回类型
//    val j = i = 42 // compile error
//    println(j)
}

fun f4() {
    val f = fun() = 42
    println(f)
}

fun f5() {
    //变量初始化和类声明都是Kotlin中的语句，它们没有声明任何返回类型
//    val c = class C // compile error
//    println(c)
}

// 与Java8的Stream API不同，Kotlin中的集合扩展函数是Eager的。
// 如果需要使用Lazy方式，可以使用sequenceOf或asSequence，序列都是使用的惰性初始化。
val x = listOf(1, 2, 3).filter {
    println("filter $it")
    it >= 2
}

val increment = { i: Int -> i + 1 }
val bicrement = { i: Int -> i + 2 }
val double = { i: Int -> i * 2 }
val one = { 1 }
private infix fun <T, R> (() -> T).then(another: (T) -> R): () -> R = { another(this()) }
operator fun <T, R1, R2> ((T) -> R1).plus(another: (T) -> R2) = { x: T -> this(x) to another(x) }

// 和 plus完全等价，是另一种书写形式
operator fun <T, R1, R2> ((T) -> R1).minus(another: (T) -> R2): (T) -> Pair<R1, R2> {
    return fun(x: T): Pair<R1, R2> = (this(x) to another(x))
}

fun main() {
    hello()
    hello1()
    hello2() // print nothing
    hello2().invoke() // Hello, World
    hello2()() // Hello, World

    println("-----")

    /*
        Hello
        \multiline world
     */
    println(
        """
        Hello
        \$world
    """.trimIndent()
    )

    println("-----")

    printNumberSign(-2)
    print(",")
    printNumberSign(0)
    print(",")
    printNumberSign(2)

    println("-----")
    run()

    println("-----")
    // List minus T：移除第一个匹配的元素。
    // List minus List：从第一个List中，移除第二个List中存在的所有元素。
    val list = listOf(1, 2, 3)
    println(list - 1)
    println(list - listOf(1))
    val ones = listOf(1, 1, 1)
    println(ones - 1)
    println(ones - listOf(1))

    println("-----")
    ({ print("Hello,") } + { print("World") })()

    println("-----")
    println(whatAmI1)
    println(whatAmI2)

    println("-----")
    println("before sum")
    println(x.sum())

    println("-----")
    val map = mapOf<Any, Any>().withDefault { "default" }
    println(map["1"]) // print null
    val map2 = mutableMapOf<String, Set<String>>().withDefault { mutableSetOf() }
    val property: Set<String> by map2 // returns empty set by default
    println(property)

    println("-----")
    val s: String? = null
    // 当s == null时，s?.isEmpty()会返回null，所以，这个表达式的返回类型应该是Boolean?，所以，不能编译通过
//  if (s?.isEmpty()) println("is empty")
    if (s?.isEmpty() == true) println("is empty")
    if (s.isNullOrEmpty()) println("is null or empty")

    println("-----")
    val x = listOf(1, 2, 3)
    // 在Kotlin中，listOf、MutableList、Java ArrayList，返回的都是java.util.List，所以它们的类型是一样的。
    println(x is List<*>) // true
//    println(x is List<String>) // not compile
    println(x is MutableList<*>) // true
    println(x is java.util.List<*>) // true

    println("-----")
    // 类似listOf、Array.asList() 这样的Helper functions它们返回的是java.util.Arrays$ArrayLis，
    // 而不是java.util.ArrayList，所以，他们是不能修改的。
    val readonly = listOf(1, 2, 3)
    if (readonly is MutableList) {
//        readonly.add(4) // throw UnsupportedOperationException
    }
    println(readonly)

    println("-----")
    val equilibrum = one then double then (increment + bicrement)
    val equilibrum2 = one then double then (increment - bicrement)
    println(equilibrum())
    println(equilibrum2())
}