package com.ssy.chapter1

fun main() {
//    var x = 10
//    var y = 20
//
//    var max: Int
//    var min: Int
//
//    if (x > y) {
//        max = x
//        min = y
//    } else {
//        max = y
//        min = x
//    }
//
//    println("max = $max, min:$min")

//    var x = 10
//    var y = 20
//
//    var max = if (x > y) x else y
//    var min = if (x > y) y else x
//    println("max = $max, min:$min")

   val x = 10
   val y = 20

    // ctrl + shit + enter 快速换行格式化，补充大括号
    var max = if (x > y) {
        println("x > y")
        x
    } else {
        println("x <= y")
        y
    }

    var min = if (x > y) {
        println("x > y")
        y
    } else {
        println("x <= y")
        x
    }
}