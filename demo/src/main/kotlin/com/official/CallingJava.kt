package com.official

import kot.relative.JavaArrayExample
import java.util.*

/*
    Java中的无参getXxx()函数和对应的setXxx(xxx)在Kotlin中自动变成property xxx。
    Boolean中的无参isXxx()函数和对应的setXxx(xxx)同样转变成property xxx。
    那如果只有set函数，没有get方法呢？ 不会被当作属性，kotlin没有只能写不能读的属性
 */
fun calendarDemo() {
    val calendar = Calendar.getInstance()
    if (calendar.firstDayOfWeek == Calendar.SUNDAY) {
        calendar.firstDayOfWeek = Calendar.MONDAY
    }

    if (!calendar.isLenient) {
        calendar.isLenient = true
    }
}

/*
    Kotlin中的关键字如in, object, is等，可能在Java中就是一个普通变量，在Kotlin中调用的话，需要加``，如
    foo.`is`(bar)

    Kotlin将Java中的变量认为是平台类型（platform types），编译器不会检查空指针，但可能运行时异常。
    有各种NonNull或Nullable注解的变量，不会认为是platform types，而是认为真实的Kotlin类型。

    详细参见官方文档：https://kotlinlang.org/docs/java-interop.html#nullability-annotations

    Java type映射到Kotlin type：
        int[] -> kotlin.IntArray!
        String[] -> kotlin.Array<(out) String>!
 */

/*
    与 Java 一样，Kotlin 的泛型不会在运行时保留：对象不携带有关传递给其构造函数的实际类型参数的信息。
    例如，ArrayList<Integer>() 与 ArrayList<Character>() 无法区分。 这使得不可能执行将泛型考虑在内的 is-checks。
    Kotlin 仅允许对星型投影泛型类型进行 is-checks：

    if (a is List<Int>) // Error: cannot check if it is really a List of Ints
    // but
    if (a is List<*>) // OK: no guarantees about the contents of the list
 */

/*
    数组与 Java 平台上的原始数据类型一起使用，以避免装箱/拆箱操作的成本。 由于 Kotlin 隐藏了这些实现细节，因此
    需要一种解决方法来与 Java 代码交互。 每种类型的原始数组（IntArray、DoubleArray、CharArray 等）都有专门的
    类来处理这种情况。 它们与 Array 类无关，并被编译为 Java 的原始数组以获得最佳性能。
 */

/*
    运算符重载，Kotlin允许拥有对应的签名和名字的Java方法重载运算操作符（和 其它通用准则如invoke() etc.），使用
    中缀表达式调用Java方法是不允许的。
 */

class CallingJava : Cloneable {
    override fun clone(): Any {
        return this
    }

    // 要覆盖 finalize()，您只需声明它，而不使用 override 关键字：
    protected fun finalize() {
        // finalization logic
    }

    // 使用JNI，使用external
    external fun foo(x: Int): Double
    var myProperty: String
        external get
        external set
}

fun main() {
    val javaObj = JavaArrayExample()
    val array = intArrayOf(0, 1, 2, 3)
    javaObj.removeIndices(array)

    // 在编译到 JVM 字节码时，编译器会优化对数组的访问，因此不会引入开销：
    val array2 = arrayOf(1, 2, 3, 4)
    array2[1] = array[1] * 2 // no actual calls to get() and set() generated
    for (x in array2) { // no iterator created
        println(x)
    }

    for (i in array.indices) { // no iterator created
        array[i] += 2
    }

    for (i in array2.indices) { // same as (i >= 0 && i < array.size)
        print(array2[i])
    }

    val array3 = intArrayOf(0, 1, 2, 3)
    javaObj.removeIndicesVarArg(*array3)

    println("-----")
    val example = JavaArrayExample::class.java
    val example2 = javaObj.javaClass
    val example3 = javaObj::class.java
    // not compiled, refer to JavaArrayExample companion object
    // val example4 = JavaArrayExample.javaClass
    println(example) // kot.relative.JavaArrayExample
    println(example2) // kot.relative.JavaArrayExample
    println(example3) // kot.relative.JavaArrayExample
    println(example === example2) // true
    println(example === example3) // true

    /*
        对于每种原始类型，都有两个不同的 Java 类，而 Kotlin 提供了获取这两个类的方法。 例如，Int::class.java 将
        返回代表原始类型本身的类实例，对应于 Java 中的 Integer.TYPE。 获取对应包装器类型的类，
        使用Int::class.javaObjectType，相当于Java的Integer.class。
     */
    val int = Int::class.java
    val int2 = Int::class.javaObjectType
    println(int) // int
    println(int2) // class java.lang.Integer
    println(int === int2) // false



}







