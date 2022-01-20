@file:Official

package com.official

import com.ssy.Official
import structure.Node

/*
    Kotlin中可能出现的空指针异常NullPointerException or NPE for short.
    * 显式地抛出 throw NullPointerException()
    * 使用!!操作符
    * 初始化方面的数据不一致，例如：
        * 构造函数中可用的未初始化 this 被传递并在某处使用（“泄漏 this”）。
        * 超类构造函数调用一个open成员，其在派生类中的实现使用未初始化状态。
    * Java和Kotlin互操作
        * 访问null引用
        * 泛型相关，如add null into MutableList<String>
        * 其它Java引起的空指针操作
 */
fun main() {
    var a: String = "abc" // Regular initialization means non-null by default
    //a = null // compilation error
    var b: String? = "abc" // can be set to null
    b = null // ok
    print(b)

    val l = if (b != null) b.length else -1

    val b2: String? = "Kotlin"
    if (b2 != null && b2.length > 0) {
        print("String of length ${b2.length}")
    } else {
        print("Empty string")
    }

    val a1 = "Kotlin"
    val b1: String? = null
    println(b1?.length)
    println(a1?.length) // Unnecessary safe call

    val listWithNulls: List<String?> = listOf("Kotlin", null)
    for (item in listWithNulls) {
        item?.let { println(it) } // prints Kotlin and ignores null
    }

    /*
    安全调用也可以放在赋值语句的左侧。 然后，如果安全调用链中的接收者之一为空，则跳过赋值，并且根本不计算右侧的表达式：
     */
    // If either `person` or `person.department` is null, the function is not called:
//    person?.department?.head = managersPool.getManager()

    // Elvis operator
    val l2 = b?.length ?: -1

    // Safe casts 如果对象不是目标类型，则常规转换可能会导致 ClassCastException。
    // 如果尝试不成功，另一种选择是使用返回 null 的安全强制转换：
    val aInt: Int? = a as? Int

    val nullableList: List<Int?> = listOf(1, 2, null, 4)
    val intList: List<Int> = nullableList.filterNotNull()
}

// 在赋值语句中，可以直接return 或者 throw exception
fun foo(node: Node): String? {
    val parent = node.data ?: return null
    val name = node.next ?: throw IllegalArgumentException("next expected")
    // ...
    return null
}
