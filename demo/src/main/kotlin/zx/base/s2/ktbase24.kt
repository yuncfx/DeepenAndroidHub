package zx.base.s2
//匿名函数和具名函数
fun main() {
    //第一步：函数输入输出的声明
    val methodAction :()->String

    //第二步：对上面函数的实现
    methodAction = {
        "derry"
        //匿名函数不要写return，最后一行就是返回
    }

    //第三步：调用函数
    println(methodAction())
    methos2()
}

//具名函数
fun methos2(): Unit {
    println("hshhs")
    
}


