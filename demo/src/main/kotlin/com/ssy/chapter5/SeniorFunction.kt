package com.ssy.chapter5

// Function vs Method， Function不依赖类，可以独立存在

/*
 * P26 - P27
 * 默认参数 default arguments
 * 在JVM上:在Kotlin中调用Java方法时不能使用具名参数语法， 因为Java字节码并不总是会保留方法参数名信息
 */
fun test(a: Int = 0, b: Int = 1) = println(a - b)

/*
 * 如果一个默认参数位于其它无默认值的参数前面， 那么默认值只能通过在调用函数时使用具名参数的方式来使用。
 *
 * 在调用函数时，如果同时使用了位置参数与具名参数，那么所有的位置参数都必须要位于第一个具名参数之前
 * 比如说，foo(1, x = 2)是允许的；foo(x = 1, 2) 是不允许的。
 */
fun test2(a: Int = 1, b: Int) = println(a - b)

/*
 * 如果函数的最后一个参数是lambda表达式，而且在调用时是位于圆括号之外，那么就可以不指定lambda表达式的具名参数名。
 */
fun test3(a: Int = 1, b: Int = 2, compute: (x: Int, y: Int) -> Unit) {
    compute(a, b)
}

/*
 * 具名参数
 * 在调用函数时， 函数参数可以是具名的
 * 当一个函数有大量参数或是一些参数拥有默认值时，这种调用方式是比较方便的。
 */
fun test4(a: Int, b: Int = 2, c: Int = 3, d: Int) = println(a + b + c + d)

/*
    可变参数， 可以使用spread操作符调用
    test4(strings = *arrayOf("a", "b", "c"))
 */
fun test4(vararg strings: String) {
    println(strings.javaClass) // strings的java类型是数组类型
    strings.forEach { println(it) }
}

fun unit(): Unit {
    return Unit
}

/**
    val list = asList(1, 2, 3)
    @param the [ts] variable has type Array<out T>.
 */
fun <T> asList(vararg ts: T) : List<T> {
    val result = ArrayList<T>()
    for (t in ts) {
        result.add(t)
    }
    return result
}

fun main() {
    test()
    test(2)
    test(b = 2)
    test(3, 2)
    test(a = 3)
    println("-----")

    println(A().method(1))
    println(B().method(2))

    println("-----")
    test2(b = 3)

    println("------")
    // 方法引用
    test3(2, 3, ::test)

    test3(2, 3, { a, b -> println(a - b) })

    test3(2, 3) { a, b ->
        println(a - b)
    }

    test3(2) { a, b ->
        println(a - b)
    }

    test3 { a, b ->
        println(a - b)
    }

    println("-----")
    test4(1, 2, 3, 4)
    test4(a = 1, b = 2, c = 3, d = 4)
    test4(a = 1, d = 5)

    println("-----")
    test4("a", "b", "c")

    /*
      星号在这里叫分散运算符（已经过期了， 现在不加星号也可以）
      可变参数可以借助于spread operator以具名参数的形式传递
     */
    test4(strings = *arrayOf("a", "b", "c"))
    // 新版本 not compile
//     test4(strings = "a")
    val arrays = arrayOf("a", "b", "c")
    // 分散运算符
    test4(*arrays)

    println("-----")
    val a = arrayOf(1, 2, 3)
    // vararg在方法中只能有一个。但是位置不一定是要在最后一个
    val list = asList(-1, 0, *a, 4)
    val a2 = intArrayOf(1, 2, 3) // IntArray is a primitive type array
    // 如果你想将一个原始类型数组传递给vararg，你需要使用toTypedArray()函数将其转换为一个常规(类型化)数组:
    val list2 = asList(-1, 0, *a2.toTypedArray(), 4)
}


/*
 * 对于重写的方法来说，子类锁拥有的重写方法会使用与父类相同的默认参数值。
 * 且子类的方法签名中必须要将默认参数值省略掉
 */
open class A {
    open fun method(a: Int, b: Int = 4) = a + b
}

class B : A() {
    override fun method(a: Int, b: Int) = a + b
}