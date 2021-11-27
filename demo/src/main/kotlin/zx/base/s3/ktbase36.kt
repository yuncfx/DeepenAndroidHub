package zx.base.s3

fun main() {

    //如果想给null怎么办？声明时指定为可空类型

    var name:String? ="zhangsan"
    name = null

    //name2是可空类型，所以会报错，必须
    val capitalize = name?.capitalize()

    println(capitalize)


}