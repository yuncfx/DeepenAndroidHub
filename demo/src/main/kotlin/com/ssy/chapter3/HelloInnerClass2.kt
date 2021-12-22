package com.ssy.chapter3

/*
    P19
    Kotlin访问外部类变量的方式：this@OuterClass.str
    Java访问外部类变量的方式 OuterClass.this.str
 */
class Person(val name: String, val age: Int) {
    private val str:String = "Person str"
    private inner class PersonFeature(var height: Int, var weight: Int) {
        private val str: String ="PersonFeature str"
        fun getPersonFeature() {
            val str: String = "local str"
            println("height: $height, weight: $weight")
            println(this@Person.str)
            println(this.str)
            println(str)
            this@Person.method()
        }
    }

    private fun method() {
        println("Person method executed!")
    }

    fun getPerson() {
        val personFeature = PersonFeature(180, 120)
        personFeature.getPersonFeature()
    }
}

fun main(args: Array<String>) {
    val person = Person("zhangsan", 20)
    person.getPerson()
}