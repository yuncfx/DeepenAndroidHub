package com.shengsiyuan.java2kot

import java.io.FileNotFoundException
import kotlin.jvm.Throws

/*
    P41
    Kotlin中，不存在受检异常(checked Exception)
 */
class NoCheckedException {
    // 不加此注解的话，Java中调用该方法，无法使用try-catch，导致运行时必须出现异常
    @Throws(FileNotFoundException::class)
    fun method() {
        println("method invoked")
        throw FileNotFoundException()
    }
}