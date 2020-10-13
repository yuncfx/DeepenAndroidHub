package base

import java.io.File

val String.lastChar: Char
    get() = get(length - 1)

fun Person.method(): String {
    println("hello extension")
    return "hello extension"
}

fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any {
        it.isHidden
    }


var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }

fun TestSplit() {
    println("12.345-6.A".split("\\.|-".toRegex()))

    println("12.345-6.A".split(".", "-"))
}

fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }

    append("\nNow I know the alphabet!")
    toString()
}

fun newAplhabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

//fun main() {
//    println("Kotlin".lastChar)
//
//    val sb = java.lang.StringBuilder("Kotlin?")
//    sb.lastChar = '!'
//    println(sb)
//
//    TestSplit()
//
//    print(Person("zhang").method())
//}