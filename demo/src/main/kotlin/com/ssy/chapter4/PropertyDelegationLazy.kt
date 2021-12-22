package com.ssy.chapter4

/*
 * P23
 * 延迟属性：指的是属性在在第一次被访问的时候被计算，之后会将之前的计算结果缓存起来供后续使用
 */

/**
 * 1. SYNCHRONIZED: 默认情况下，延迟属性的计算是同步的，值只会在一个线程中计算，所有线程都会得到相同的结果。
 * 2. PUBLICATION: 如果不需要初始化委托的同步，这样多个线程可以同时执行。
 * 3. NONE: 如果确定初始化操作只在一个线程中执行，这样会减少线程安全方面的开销。
 */
val myLazyValue: Int by lazy(LazyThreadSafetyMode.NONE) {
    println("hello world")
    30
}

fun main(args: Array<String>) {
    println(myLazyValue)
    println(myLazyValue)
}