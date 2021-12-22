package com.ssy.coroutines

fun main() {
    test(5, action = {
        println("Hello world")
    })

    test2(5, action = {
        println(it)
        println("Hello world")
    })

    test(5, ::test3)

    test2(5, ::test4)

    test5(5, ::test6)

/*    test5(5, action= {
        println("a")
    })*/

    test5(5) { x, y ->
        println("hello")
        println(x + y)
    }
}

fun test(x: Int, action: () -> Unit) {
    action()
}

fun test2(x: Int, action: (Int) -> Unit) {
    action(x)
}

fun test3() {

}

fun test4(x: Int) {

}

fun test5(x: Int, action: (Int, Int) -> Unit) {
    action(1, 2)
}

fun test6(x: Int, y: Int) {
    println(x + y)
}