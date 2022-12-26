package com.official

import junit.framework.TestCase
import org.junit.Assert
import org.junit.Test
import java.lang.RuntimeException
import kotlin.properties.Delegates

class SampleTest : TestCase() {
    private val testSample: Sample = Sample()

    private var soundEffectNode :Int by Delegates.observable(-11){_,old,new->
        println(("soundEffectNodeValue = old = $old, new:$new"))
        val elements = Thread.currentThread().stackTrace
        for (i in 1 until elements.size) {
            val s = elements[i]
            println(
                "\tat " + s.className + "." + s.methodName + "(" + s.fileName + ":" + s.lineNumber + ")"
            )
        }
    }
    @Test
    fun testSum() {
        val expected = 42
        Assert.assertEquals(expected, testSample.sum(40, 2))
    }
    @Test
    fun testDefault() {
        t(12)
    }
    fun t(i:Int = 0, j:Int = i) {
        println("i:$i, j:$j")
    }
    @Test
    fun testRunCatching() {
        val list = mutableListOf<String>()
        println(list.runCatching { list[0] }.getOrNull())
    }
    @Test
    fun testDelegates() {
        soundEffectNode = 12
    }
}