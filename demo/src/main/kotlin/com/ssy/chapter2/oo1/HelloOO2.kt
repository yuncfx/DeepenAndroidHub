package com.ssy.chapter2.oo1

class Person constructor(userName: String) {
    private var userName: String
    private var age: Int
    private var address: String

    init {
        println(userName)
        this.userName = userName
        this.age = 20
        this.address = "Beijing"
    }

    constructor(userName: String, age: Int) : this(userName) {
        println("username:$userName, age:$age")
        this.userName = userName
        this.age = age
        this.address = "Beijing"
    }

    constructor(userName: String, age: Int, address: String) : this(userName, age) {
        this.address = address
    }

    fun printInfo() {
        println("username:$userName, age:$age, address:$address")
    }

}

fun main(args: Array<String>) {
    val person = Person("zhangsan")
    val person2 = Person("lisi", 30)
    val person3 = Person("wangwu", 40, "hangzhou")
    person.printInfo()
    person2.printInfo()
    person3.printInfo()
}