package grammer

val DemoDemo<String>.java: String
    get() = "hello"

class DemoDemo<T : Any?> {
    fun convert(value: T): Unit {
        if (value != null) {
            val t = value as Any
            println(t::class.java)
        }

        val str: String = "hello"
        str::class.java
    }
//
//    fun convert2(value: T): Unit {
//        value::class
//    }
}

fun main() {
    DemoDemo<String>().convert("String")

    println(DemoDemo<String>().java)
}