package com.ssy.chapter2.oo2

/*  P18
    star projection (星投影)

    Star<out T>: 如果T的上界是TUpper，那么Star<*>就相当于Star<out T>, 这表示当T的类型未知时，你可以
    从Star<*>中安全地读取TUpper类型的值

    Star<in T>: Star<*>就相当于Star<in Nothing>,这表示你无法向其中写入任何值

    Star<T>: 如果T的上界为TUpper，那么Star<*>就相当于读取时的Star<out TUpper>以及写入时的Star<in Nothing>
 */

class Star<out T> {

}

class Star2<in T> {
    fun setValue(t: T) {}
}

class Star3<T>(private var t: T) {
    fun setValue(t: T) {

    }

    fun getValue(): T {
        return this.t
    }
}

fun main(args: Array<String>) {
    val star: Star<Number> = Star<Int>()
    val star2: Star<*> = star

    val star3: Star2<Int> = Star2<Number>()
    val star4: Star2<*> = star3

    // star4.setValue(3) // compile error

    val star5: Star3<String> = Star3<String>("hello")
    val star6: Star3<*> = star5

    star6.getValue()
    // star6.setValue("world") // compile error

    val list: MutableList<*>  = mutableListOf("hello", "world", "hello world")
    println(list[0])

//    list[0] = "test" // compile error
}