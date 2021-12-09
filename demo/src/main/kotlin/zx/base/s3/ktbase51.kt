package zx.base.s3
//let内置函数
//    //let函数返回类型根据匿名函数的返回类型变化而变化，why？
//    //let函数的匿名函数里面持有it，等于集合本身
//standard.kt
fun main() {
//    val listOf:List<Int> = listOf(6, 5, 2, 3)
    //自动类型推断
    val list = listOf(6, 5, 2, 3)

    val first = list.first()

    println("first:$first")

//let函数调用的这个lambda函数的最后一行是返回值，可是为什么也是let函数返回值？
    //let函数返回类型根据匿名函数的返回类型变化而变化，why？
    //匿名函数里面持有it
    val let : Int= listOf(6, 5, 2, 3, 2, 7).let {
        //it 是list集合
        println("it 是 ：$it")
        it.first() + it.first()
    }

    println(let)


}