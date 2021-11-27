package zx.base

fun main() {
    `测试登录功能，编写人`("sjsjj")
}

fun show(number: Int): Unit {
    when(number){
        //这里不是注释提示，会终止程序的
        -1 -> TODO("没有这种分数")
        in 0..59 -> println("不及格")
        in 60..70 -> println("及格")
        in 71..100 -> println("优秀")
    }
    
}

fun `测试登录功能，编写人`(name:String){
    println("模拟，name是${name}")
}