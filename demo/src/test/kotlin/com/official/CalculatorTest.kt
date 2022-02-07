package com.official

import junit.framework.TestCase

class CalculatorTest : TestCase() {
    var calculator: Calculator? = null
    public override fun setUp() {
        println("setUp")
        calculator = Calculator()
    }

    public override fun tearDown() {
        println("tearDown")
    }

    fun testSum() {
        assertEquals(2.0, calculator?.sum(1.0, 1.0) ?: 1.0, 0.0)
    }
}