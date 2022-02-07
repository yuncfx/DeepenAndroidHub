package com.ssy.chapter1

fun main(args: Array<String>) {
    val array: IntArray = intArrayOf(1, 2, 3, 4, 5)

    for (item: Int in array) {
        println(item)
    }

    println("-----")

    for (i in array.indices) {
        println("array[$i]:${array[i]}")
    }

    println("-----")
    for ((index, value) in array.withIndex()) {
        println("array[$index]: $value")
    }

    println("-----")

    val price = """
${'$'}_9.99
"""
    println(price)

    println("-----")

    val text = """
     |理论是你知道是这样，但它却不好用。
     |实践是它很好用，但你不知道是为什么。
     |程序员将理论和实践结合到一起：
     |既不好用，也不知道是为什么。
   """
    println(text.trimMargin())

    println("-----")
    val text2 = """
           |hello
       |world!
   """
    // indent 删除空格，直到遇见第一个|或第一个非空白字符
    println(text2.trimIndent())
    /*
         margin 删除空格，如果有|，会删除直到|，否则删除到第一个非空白字符,
         可以传递自定义参数如"#"，则"#"会替换掉"|"
     */
    println(text2.trimMargin())
    println(text2.trimMargin("#"))

    val text3 = """
            hello
        world3    
    """.trimIndent()
    println("--- print text3 ---")
    println(text3.trimIndent())
    println(text3.trimMargin())
}