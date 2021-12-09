package zx.base.s3
//apply 内置函数
//apply 函数始终是返回info本身
//apply函数的匿名函数里面持有的是this，等于info本身
//standard.kt
fun main() {


    val  info = "hsjsjjj"

    println(info[info.length-1])

    val apply = info.apply {
        //apply函数不持有it，确持有当前this
        println("apply 匿名函数里面打印 info 本身：$this")
        println(this.length)
        println(this.toUpperCase())
        println(this)
    }

    println("apply 返回的值: $apply")

    //apply函数的作用？apply返回info本身，可以多次链式调用apply


    info.apply{
        println("长度是$length")
    }.apply {

    }.apply {

    }



}