package com.shengsiyuan.chapter2.oo1

/**
 *  var propertyName: propertyType = initializer
 *      getter()...
 *      setter()...
 *
 *  backing field, 支撑字段（支撑域）
 *  backing property, 支撑属性
 */
class ThePerson {
    val age: Int = 20

    // private int age
}

class ThePerson2(address: String, name: String) {
    val age: Int
        get() = 20

    // field只能在get和set访问器中使用
    var address: String = address
        get() {
            println("get invoked!")
            return field
        }
        set(value) {
            println("set invoked!")
            field = value
        }

    var name:String = name
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var pName: String = name
        private set

    // lombok工具
    // @Getter
    // @Setter
    // @Data
    // class LombokDemo
}

fun main(args: Array<String>) {
    val person = ThePerson()
    println(person.age)
    val person2 = ThePerson2("shanghai", "zhangsan")
    println(person2.age)

    println(person2.address)
    person2.address = "tianjin"
    println(person2.address)

    println(person2.name)
    person2.name = "lisi"
    println(person2.name)
}