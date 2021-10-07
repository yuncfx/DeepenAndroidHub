package com.shengsiyuan.chapter3

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import javax.swing.JButton
import javax.swing.JFrame

/**
 * P21, P22
 */
fun main(args: Array<String>) {
    val jFrame = JFrame("My JFrame")
    val jButton = JButton("My JButton")
    jFrame.addWindowListener(object : WindowListener {
        override fun windowOpened(p0: WindowEvent?) {
            println("windowOpened")
        }

        override fun windowClosing(p0: WindowEvent?) {
            println("windowClosing")
        }

        override fun windowClosed(p0: WindowEvent?) {
        }

        override fun windowIconified(p0: WindowEvent?) {
        }

        override fun windowDeiconified(p0: WindowEvent?) {
        }

        override fun windowActivated(p0: WindowEvent?) {
            println("windowActivated")
        }

        override fun windowDeactivated(p0: WindowEvent?) {
        }

    })

    /**
     * 如果对象是Java函数式接口的一个实例（即只有一个抽象方法的接口），
     * 那么可以通过Lambda表达式来调用，其中Lambda表达式前面加上接口的类型。
     */
//    jButton.addActionListener(object : ActionListener {
//        override fun actionPerformed(p0: ActionEvent?) {
//            println("button pressed")
//        }
//    })

    jButton.addActionListener(ActionListener {
        println("button pressed")
    })

    jButton.addActionListener({ e -> println("button pressed") })

    jButton.addActionListener {
        println("button pressed")
    }

    val listener = ActionListener { println("hello world") }
    println(listener.javaClass)
    println(listener.javaClass.superclass)

    println(listener::class.java)
    println(listener::class.java.superclass)

    jFrame.add(jButton)
    jFrame.pack()
    jFrame.isVisible = true
    jFrame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
}