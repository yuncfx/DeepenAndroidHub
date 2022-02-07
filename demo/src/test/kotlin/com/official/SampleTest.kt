package com.official

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test


class SampleTest : TestCase() {
    private val testSample: Sample = Sample()

    @Test
    fun testSum() {
        val expected = 42
        Assert.assertEquals(expected, testSample.sum(40, 2))
    }
}