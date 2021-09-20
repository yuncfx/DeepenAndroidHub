package com.shengsiyuan.chapter2.oo

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
    val student: Student = Student("zhangsan", 20, "shenzhen")
    student.printInfo()

    var student3 = Student3()
    println(student3.userName)
    student3 = Student3("lisi")
    println(student3.userName)
}