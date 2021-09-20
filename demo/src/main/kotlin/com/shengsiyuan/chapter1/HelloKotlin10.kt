package com.shengsiyuan.chapter1

/**
 * trimIndent, trimMargin的区别
 * todo
 */
fun main(args: Array<String>) {
    var a: String = "hello \n world"
    println(a)

    var b: String = """    hello 
        \n world
        welcome
    """.trimIndent()
    println(b)
}