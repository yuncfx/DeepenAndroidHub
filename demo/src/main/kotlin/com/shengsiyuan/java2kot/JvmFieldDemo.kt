package com.shengsiyuan.java2kot

/*
 * 使用@JvmField注解对Kotlin中的属性进行标注时，表示它是一个实例字段(instance field)，Kotlin编译器在处理的时候，将不会
 * 给这个字段生成getter/setter
 */
class Person {
    var name: String = "zhangsan"

    @JvmField
    var age: Int = 10
}