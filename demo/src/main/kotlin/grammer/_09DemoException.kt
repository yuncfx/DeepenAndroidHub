package grammer

import java.lang.RuntimeException

fun main() {
    try {
        throw RuntimeException()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        println("God finally1")
    }

    assert(true == true)
}