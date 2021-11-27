package zx.base

fun main() {
    val week = 1
    val info = when (week){
        1 ->  "今天星期1"
        2 -> "今天星期2"
        else ->{
            println("养猪去了")
        }
    }

    //如果when 是7，此时info打印出来是kotlin.unit，unit是kotlin一个类
    //如果when 是1，此时info是Sting类型
    //我理解，String和unit都是any类型的子类？？？？
    println(info)
}