package grammer

import java.lang.RuntimeException
const  val TAG: String = "hello"

fun main() {
    println(TAG)
    try {
        throw RuntimeException()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        println("God finally1")
    }

    assert(true == true)
}