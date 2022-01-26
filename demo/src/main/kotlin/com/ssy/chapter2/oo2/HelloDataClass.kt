package com.ssy.chapter2.oo2

// P13 P50总结复习到了
// 数据类 - data class
// lombok

/**
 * 数据类需要满足如下要求：
 * 1. 主构造方法至少要有一个参数
 * 2. 所有的主构造方法参数都需要被标记为var或是val
 * 3. 数据类不能是抽象的、open的、sealed的以及inner的
 *
 * 对于数据类型，编译器会自动生成如下内容：
 * 1. equals/hashCode对
 * 2. toString()方法, 形式为Person(name=..., age=..., address=...)
 * 3. 针对属性的componentN方法，并且是按照属性的声明顺序来生成的。
 * 4. copy方法(默认是浅拷贝)
 *
 * 关于数据类成员的继承要点：
 * 1. 如果数据类中显示定义了equals，hashCode或是toString方法，或是在数据类的父类中将
 * 这些方法声明为了final， 那么这些方法就不会再生成，转而使用已有的。
 * 2. 如果父类拥有componentN方法，并且是open的以及返回兼容的类型，那么编译器就会在数据类
 * 中生成相应的componentN方法，并且重写父类的这些方法；如果父类中的这些方法由于不兼容的签名
 * 或是被定义为final的，那么编译器就会报错。
 * 3. 在数据类中显示提供componentN方法以及copy方法实现是不允许的。
 *
 * 解构声明：
 * 数据类在主构造方法中有多少个参数，就会依次生成对应的component1, component2, component3...
 * 这些方法返回的就是对应字段的值，componentN方法就是用来实现解构声明的。
 * component() 函数需要用 operator 关键字标记，以允许在解构声明中使用它们。
 */
data class Person(val name: String, var age: Int, var address: String)

/**
 * 在JVM平台上， 如果生成的类需要拥有无参构造方法，那么就需要为所有属性指定默认值。
 */
data class Person2(val name: String = "", var age: Int = 0, var address: String = "")

fun main(args: Array<String>) {
    val person = Person("zhangsan", 20, "Beijing")
    println(person)

    person.age = 30
    println(person.age)

    val person2 = person.copy(address = "hangzhou")
    println(person2)

    // 解构赋值要按参数顺序来
    val (name, age, address) = person
    println("name:$name, age:$age, address:$address")
}
