@file:Official

package com.ssy.chapter5

import com.ssy.Official
import java.awt.Button

/*
    一等公民 functions, functions可以出现在任何其它属性能出现的地方
    You can perform any operations on functions that are possible for other non-function values.
 */

fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

class A1
class B1
class C1

lateinit var f1: (A1, B1) -> C1
lateinit var f2: A1.(B1) -> C1
lateinit var f3: (Int) -> ((Int) -> Unit)

// 箭头是向右结合的， f4等价于f3
lateinit var f4: (Int) -> (Int) -> Unit
typealias ClickHandler = (Button, B1) -> Unit


/*
    Instantiating a function type 初始化一个函数类型
    在函数字面量中使用下列形式之一的代码块：
        lambda表达式 {a, b -> a + b}
        匿名函数 fun(s: String): Int {return s.toIntOrNull() ?: 0}
    使用一个可被调用的引用：
        a top-level, local, member, or extension function ::isOdd, String::toInt
        a top-level, member, or extension property List<Int>::size
        a constructor ::Regex
    使用实现函数类型的自定义类的实例作为接口：
        class IntTransformer : (Int) -> Int {
            override operator fun invoke(x: Int): Int = TODO()
        }

        val intFunction: (Int) -> Int = IntTransformer()
 */

class IntTransformer : (Int) -> Int {
    override operator fun invoke(x: Int): Int = TODO()
}

val intFunction: (Int) -> Int = IntTransformer()

val a = { i: Int -> i + 1 } // // The inferred type is (Int) -> Int

/*
    有和没有接收器的函数类型的非字面值是可以互换的，所以接收器可以代表第一个参数，反之亦然。
    Non-literal values of function types with and without a receiver are interchangeable,
    so the receiver can stand in for the first parameter, and vice versa.
*/
val repeatFun: String.(Int) -> String = { times -> this.repeat(times) }
val twoParameters: (String, Int) -> String = repeatFun // ok
fun runTransformation(f: (String, Int) -> String): String {
    return f("hello", 3)
}

val result = runTransformation(repeatFun)

/*
    Invoking a function type instance
    A value of a function type can be invoked by using its invoke(...) operator: f.invoke(x)
    or just f(x).
 */
val stringPlus: (String, String) -> String = String::plus
val intPlus: Int.(Int) -> Int = Int::plus

/*  lambda expression syntax, lambda表达式语法：
    1. lambda表达式总是被大括号包着
    2. 如果返回类型不是Unit，则最后一行的表达式的值被认为是返回值
 */
val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
val sum2 = { x: Int, y: Int -> x + y }

// Anonymous functions 匿名函数, 需要确切指定函数返回类型时
val anonymous = fun(x: Int, y: Int): Int = x + y
val anonymous2 = fun(x: Int, y: Int): Int {
    return x + y
}

// 匿名函数 vs lambda
/*
    没有标签的 return 语句总是从用 fun 关键字声明的函数返回。
    这意味着 lambda 表达式内的 return 将从封闭函数返回，而匿名函数内的 return 将从匿名函数本身返回。
 */

fun main() {
    val items = listOf(1, 2, 3, 4, 5)
    // Lambdas are code blocks enclosed in curly braces.
    items.fold(0, {
        // When a lambda has parameters, they go first, followed by '->'
            acc: Int, i: Int ->
        println("acc=$acc, i=$i")
        val result = acc + i
        println("result:$result")
        // The last expression in a lambda is considered the return value:
        result
    })

    // Parameter types in a lambda are optional if they can be inferred:
    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })

    // Function references can also be used for higher-order function calls:
    val product = items.fold(1, Int::times)

    // Invoking a function type instance
    println(stringPlus.invoke("<-", "->"))
    println(stringPlus("Hello, ", "world!"))

    println(intPlus.invoke(1, 1))
    println(intPlus(1, 2))
    println(2.intPlus(3)) // extension-like call

    // Lambda 表达式和匿名函数都是函数字面量。 函数字面量是未声明但作为表达式立即传递的函数
    // 第二个参数就是函数字面量
    val strings = listOf("hello", "world1", "max")
    max(strings, { a, b -> a.length < b.length })
    max(strings, ::compare)

    // trailing lambda
    val product2 = items.fold(1) { acc, e -> acc * e }
    // 如果lambda是唯一参数，甚至可以将参数的小括号省略掉
    run { println("...") }

    // 如果编译器能解析签名而不需要任何参数，参数可以不声明，且->可以省略
    items.filter { it > 0 } // this literal is of type '(it: Int) -> Boolean'

    // Returning a value from a lambda expression
    items.filter {
        val shouldFilter = it > 0
        shouldFilter
    }
    items.filter {
        return@filter it > 0
    }
    // lambda最后一行作为返回值，方便链式调用
    val t = items.filter { it > 2 }.sortedBy { it }.map { it.toString() }
    println(t) // [3,4,5]

    // 没有使用到的参数，可以使用_代替
    // map.forEach { _, value -> println("$value!") }

    // 匿名函数, 匿名函数不允许放到小括号外面。
    items.filter(fun(item) = item > 0)

    // Closures 闭包
    var sum = 0
    items.filter { it > 0 }.forEach(fun(it) { sum += it })

    // Kotlin 提供了在提供接收器对象的同时调用具有接收器的函数类型实例的能力。
    val sum2: Int.(Int) -> Int = { other -> plus(other) }
    /*
        匿名函数语法允许您直接指定函数字面量的接收者类型。
        如果您需要使用接收器声明函数类型的变量，然后稍后使用它，这将很有用。
     */
    val sum3 = fun Int.(other: Int): Int = this + other

    /*
        当可以从上下文中推断出接收器类型时，Lambda 表达式可以用作带有接收器的函数字面量。
        它们使用的最重要的例子之一是类型安全的构建器：
     */
    html { // lambda with receiver begins here
        body() // calling a method on the receiver object
        this.body()
        this.body2()
    }

    html(fun(html: HTML) {
        html.body()
        html.body()
        html.body2()
    })
}

fun compare(a: String, b: String): Boolean = a.length < b.length

fun max(list: List<String>, block: (String, String) -> Boolean): String {
    var max = list.first()
    for (i in list) {
        for (j in list) {
            if (block(i, j)) {
                max = i
            }
        }
    }
    return max
}

class HTML {
    fun body() {

    }

    fun body2(): Int {
        return 0
    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

