@file:Official

package com.official

import com.ssy.Official

fun printLine() {
    println("Top-level function")
}

class A { // implicit label @A
    inner class B { // implicit label @B
        fun Int.foo() { // implicit label @foo
            val a = this@A // A's this
            val b = this@B // B's this

            val c = this // foo()'s receiver, an Int
            val c1 = this@foo // foo()'s receiver, an Int

            val funLit = lambda@ fun String.() {
                val d = this // funLit's receiver
            }

            val funLit2 = { s: String ->
                // foo()'s receiver, since enclosing lambda expression
                // doesn't have any receiver
                val d1 = this
            }
        }
    }

    private fun printLine() {
        println("member function")
    }

    fun invokePrintLine(omitThis: Boolean = false) {
        if (omitThis) printLine()
        else this.printLine()
    }
}

fun main() {
    A().invokePrintLine()
    A().invokePrintLine(omitThis = true)
}