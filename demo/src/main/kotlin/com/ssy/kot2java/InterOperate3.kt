package com.ssy.kot2java

import kot.relative.MyVarargs

fun main() {
    val myVarargs = MyVarargs()
    val stringArrays = arrayOf("hello", "world", "hello world")

    myVarargs.myMethod(*stringArrays) // spread operation *
}