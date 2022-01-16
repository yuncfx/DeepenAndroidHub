@file:Official

package com.ssy.chapter5

import com.ssy.Official
import com.ssy.chapter4.*

/*
    自定义操作符
    Kotlin 允许您为类型上预定义的一组运算符提供自定义实现。
    这些运算符具有预定义的符号表示（如 + 或 *）和优先级。
    要实现运算符，请为相应类型提供具有特定名称的成员函数或扩展函数。
    这种类型成为二元运算的左侧类型和一元运算的参数类型。
 */
interface IndexedContainer {
    operator fun get(index: Int)
}

class OrdersList: IndexedContainer {
    // 重载operator之后，operator关键字可以省略
    override fun get(index: Int) {
        return
    }
}

/*
    Unary operations
    一元操作符
    +a  >>> a.unaryPlus()
    -a  >>> a.unaryMinus()
    !a  >>> a.not()

    以+a举例，解析对应的方法的流程如下：
        确定a的类型，设为T。
        查找带有操作符修饰符且没有接收器 T 参数的函数 unaryPlus()，这表示成员函数或扩展函数。
        如果函数不存在或不明确，则为编译错误。
        如果函数存在且其返回类型为 R，则表达式 +a 的类型为 R。
    这些操作以及所有其他操作都针对基本类型进行了优化，并且不会为它们引入函数调用的开销。
 */
data class Point(val x: Int, val y: Int)

operator fun Point.unaryMinus() = Point(-x, -y)
val point = Point(10, 20)

/*
    increments and decrements
    a++  >>>  a.inc()
    a--  >>>  a.dec()

    inc() 和 dec() 函数必须返回一个值，该值将分配给使用 ++ 或 -- 操作的变量。 他们不应该改变调用 inc 或 dec 的对象。

    编译器执行以下步骤以解析后缀形式的运算符，例如 a++：
        确定a的类型，设为T。
        查找带有操作符修饰符且无参数的函数 inc()，适用于类型 T 的接收器。
        检查函数的返回类型是否是 T 的子类型。
        计算表达式的效果是：
            将 a 的初始值存储到临时存储器 a0 中。
            将 a0.inc() 的结果赋值给 a。
            返回 a0 作为表达式的结果。
            对于 a-- 步骤完全类似。
    对于前缀形式 ++a 和 --a 解析的工作方式相同，效果是：
        将 a.inc() 的结果分配给 a。
        作为表达式的结果返回 a 的新值。
 */

/*
    二元操作符
    binary operations

    a + b  >>>  a.plus(b)
    a - b  >>>  a.minus(b)
    a * b  >>>  a.times(b)
    a / b  >>>  a.div(b)
    a % b  >>>  a.rem(b)
    a..b   >>>  a.rangTo(b)
 */
data class Counter(val dayIndex: Int) {
    operator fun plus(increment: Int) : Counter {
        return Counter(dayIndex + increment)
    }
}

/*
    in operator
    a in b  >>>  b.contains(a)
    a !in b >>>  !b.contains(a)
 */

/*
    indexed access operator
    a[i]  >>>  a.get(i)

    a[i, j]  >>>  a.get(i, j)

    a[i_1, ..., i_n]  >>>  a.get(i_1, ..., i_n)

    a[i] = b  >>>  a.set(i, b)

    a[i, j] = b  >>>  a.set(i, j, b)

    a[i_1, ..., i_n] = b  >>>  a.set(i_1, ..., i_n, b)
 */

/*
    invoke operator
    a()  >>>  a.invoke()

    a(i)  >>>  a.invoke(i)

    a(i, j)  >>>  a.invoke(i, j)

    a(i_1, ..., i_n)  >>>  a.invoke(i_1, ..., i_n)
 */

/*
    Augmented assignments
    a += b  >>>  a.plusAssign(b)

    a -= b  >>>  a.minusAssign(b)

    a *= b  >>>  a.timesAssign(b)

    a /= b  >>>  a.divAssign(b)

    a %= b  >>>  a.remAssign(b)

    增强型赋值操作符解析原理：
    对于赋值操作，例如 a += b，编译器执行以下步骤：
        如果右列的功能可用：
            1. 如果对应的二进制函数（即 plusAssign() 的 plus()）也可用，a 是可变变量，而 plus
            的返回类型是 a 类型的子类型，则报告错误（歧义）。
            2. 确保其返回类型为Unit，否则报错。
            3. 为 a.plusAssign(b) 生成代码。
        否则，尝试为 a = a + b 生成代码（这包括类型检查：a + b 的类型必须是 a 的子类型）。
    在Kotlin中，赋值不是语句， 因此如下语法编译不通过
    val a = b = c = 0
 */

/*
    Equality and inequality operators
    a == b  >>>  a?.equals(b) ?: (b === null)
    a != b  >>>  !(a?.equals(b) ?: (b === null))

    这些运算符仅适用于函数 equals(other: Any?): Boolean，可以重写该函数以提供自定义相等检查实现。
    任何其他具有相同名称的函数（如 equals(other: Foo)）都不会被调用。

    == 操作很特殊：它被转换为一个复杂的表达式，用于筛选空值。 null == null 始终为真，
    非 null x 的 x == null 始终为假，不会调用 x.equals()。
 */

/*
    Comparison operators
    a > b  >>>  a.compareTo(b) > 0

    a < b  >>>  a.compareTo(b) < 0

    a >= b  >>>  a.compareTo(b) >= 0

    a <= b  >>>  a.compareTo(b) <= 0

    必须返回Int类型
 */

/**

 */

/**
 *  Property delegation operators 代理属性操作符
 *  provideDelegate, getValue and setValue operator functions are described in Delegated properties.
 *  参见 DelegationSummary.kt
 */

/**
    Infix calls for named functions 中缀函数，see [InfixTest]
    您可以使用中缀函数调用来模拟自定义中缀操作。
 */
fun main() {
    println(-point) // prints "Point(x=-10, y=-20)"
}

