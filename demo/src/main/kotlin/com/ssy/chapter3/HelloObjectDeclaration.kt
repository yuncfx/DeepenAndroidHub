package com.ssy.chapter3

import java.awt.event.WindowEvent
import java.awt.event.WindowListener

/*
    P22 对象声明
    对象表达式与对象声明之间的区别：
    1. 对象表达式是立刻初始化或是执行的
    2. 对象声明是延迟初始化的，在首次访问的时候进行
    3. 伴生对象是在其所对应的类被加载的时候初始化的，对应于Java的静态初始化。
 */

object MyObject {
    fun method() = "hello world"
}

object MyObject2: WindowListener {
    override fun windowOpened(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowClosing(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowClosed(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowIconified(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowDeiconified(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowActivated(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

    override fun windowDeactivated(p0: WindowEvent?) {
        TODO("Not yet implemented")
    }

}

fun main(args: Array<String>) {
    println(MyObject.method())
}