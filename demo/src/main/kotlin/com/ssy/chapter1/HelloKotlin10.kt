package com.ssy.chapter1

/**
 * trimIndent, trimMargin的区别
 */
fun main(args: Array<String>) {
    val a: String = "hello \n world"
    println(a)
    println("-------------------")

    val b: String = """    hello 
        \n world
        welcome
    """

    val c: String = """    hello 
        \n world
        welcome
    """
    println(b.trimIndent())
    println(c.trimMargin())
}