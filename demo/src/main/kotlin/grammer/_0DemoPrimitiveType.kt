package grammer

import java.util.*

// 基本类型
var c: Char = 'a'
var i: Int = 1024
var b: Byte = 1
var s: Short = 2
var l: Long = 1023L
var f: Float = 1.1F
var d: Double = 1.5
var z: Boolean = false
var string: String = "Hello, World"

val readableValue = 1022
val _readableValue = "not modified"
val java = 19
val kotlin = 20


// comments
/**
 * comments2
 */

/*
  comments3
 */

/*
/*
 embedded comments4
 */
 */

fun main() {
    convert()
    operator()
    input()
}

/**
 * 数据类型转化
 * 除了Boolean值特殊， 基本可以互相转化
 */
fun convert(): Unit {
    println(c.toByte())
    println(c.toShort())
    println(c.toInt())
    println(c.toFloat())
    println(c.toDouble())
    println(c.toLong())

    println(b.toChar())
    println(b.toInt())
    println(b.toShort())

    println(d.toInt())
}

fun operator() {
    println((i + i))
    println((i - i))
    println((i * i))
    println((i / i))
    println(i % i)

//    i += i
//    i -= i
//    i *= i
//    i /= i
//    i %= i

    println(i > i)
    println(i < i)
    println((i >= i))
    println((i <= i))

    println((string == string))
    println((string === string))
    println((string != string))
    println((string !== string))

    println((i xor i))
    println((i or i))
    println((i and i))
    // 取反
    println(i.inv())

    println(i.shl(2))
    println(i.shr(2))
    println(i.ushr(2))
}

// System input
fun input() {
    val scanner = Scanner(System.`in`)
    val nextLine = scanner.nextLine()
    println(nextLine)
}