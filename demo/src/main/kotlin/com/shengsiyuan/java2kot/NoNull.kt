package com.shengsiyuan.java2kot

class NoNull {
    // 被Java调用的方法，尽量使用可空类型
    fun method(str: String?) {
        println("method invoked")
        println(str)
    }
}