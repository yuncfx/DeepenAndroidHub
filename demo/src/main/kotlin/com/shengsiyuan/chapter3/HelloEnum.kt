package com.shengsiyuan.chapter3

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

fun main(args: Array<String>) {

    val seasons = Season.values()
    seasons.forEach { season: Season -> println(season) }
    // it表示当前正在迭代的对象
    seasons.forEach { println(it) }

    println("-----")
    val season = Season.valueOf("SPRING")
    println(season.name)
}