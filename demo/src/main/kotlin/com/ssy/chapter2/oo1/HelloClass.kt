package com.ssy.chapter2.oo1

class EmptyClass

// 在Kotlin中，一个类可以有一个primary构造方法以及一个或多个secondary构造方法

// primary构造方法是类头(class header)的一部分，它位于类名后面，可以拥有若干参数
// 如果primary构造方法没有任何注解或是可见性关键字修饰，那么constructor关键字可省略
class MyClass constructor(userName: String) {
    private val userName = userName.uppercase()
    init {
        println(userName)
    }
}

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

class Student(private val userName: String, private val age: Int, private var address: String) {
    fun printInfo() {
        println("userName:$userName, age:$age, address:$address")
    }
}

/**
 * 如果构造方法拥有注解或可见性修饰符，那么constructor关键字就不能省略，并且它位于修饰符后面
 */
class Student2 private constructor(userName: String)

/**
 * 在JVM上，如果类的primary构造方法的每个参数都有默认值，编译器会自动生成一个默认构造方法， 该默认构造方法使用这些默认值作为初始化值。
 * 这样做的目的在于可以跟Spring等框架更好地集成。
 */
class Student3(val userName: String = "zhangsan") {
}

fun main(args: Array<String>) {
    val person = Person("zhangsan")
    val person2 = Person("lisi", 30)
    val person3 = Person("wangwu", 40, "hangzhou")
    person.printInfo()
    person2.printInfo()
    person3.printInfo()

    val  myClass = MyClass("zhangsan")

    val student: Student = Student("zhangsan", 20, "shenzhen")
    student.printInfo()

    var student3 = Student3()
    println(student3.userName)
    student3 = Student3("lisi")
    println(student3.userName)
}