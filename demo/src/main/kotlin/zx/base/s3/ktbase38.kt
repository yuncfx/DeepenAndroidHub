package zx.base.s3

fun main() {

    var name:String?=null

    //!!断言 不管name 是不是null，都执行，但是未null的话会崩溃的
    //如果百分百保证name是有值的，就可以使用断言，不会崩溃，否则有java空指针异常的风险
    var r = name!!.capitalize()

    println(r)




}