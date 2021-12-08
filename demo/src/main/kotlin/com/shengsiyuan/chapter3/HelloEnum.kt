package com.shengsiyuan.chapter3

import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/*
 * P22
 * 枚举
 */
enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}

enum class Season2(val temperature: Int) {
    SPRING(10), SUMMER(30), AUTUMN(20), WINTER(-10)
}

enum class Season3 {
    SPRING {
        override fun getSeason(): Season3 = this
    },

    SUMMER {
        override fun getSeason(): Season3 = this
    },

    AUTUMN {
        override fun getSeason(): Season3 = this
    },

    WINTER {
        override fun getSeason(): Season3 = this
    }
    ;

    abstract fun getSeason(): Season3
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

/**
 * 枚举实现接口
 */
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        // 单独为对象实现方法
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        // 单独为对象实现方法
        override fun apply(t: Int, u: Int): Int = t * u
    };

    // 为所有对象默认实现该方法
    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}

/**
 * 您可以使用 enumValues<T>() 和 enumValueOf<T>() 函数以通用方式访问枚举类中的常量：
 */
inline fun <reified T : Enum<T>> printAllValues() {
    println(enumValues<T>().joinToString { it.name })
}

fun main(args: Array<String>) {

    val seasons = Season.values()
    seasons.forEach { season: Season -> println(season) }
    // it表示当前正在迭代的对象
    seasons.forEach { println(it) }

    println("-----")
    val season = Season.valueOf("SPRING")
    println(season.name)

    printAllValues<Season>()

    println(Season.AUTUMN.name)
    println(Season.AUTUMN.ordinal)
}