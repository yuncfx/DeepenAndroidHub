package zx.base.s2

fun main() {

    //showMethod是一个函数，所以可以调用这个函数
    val showMethod = showMethod("show");
    println(showMethod("zhangxuan",20))


}

//函数返回一个匿名函数
fun showMethod(info:String):(String,Int)->String{
    println("test")

    return {
        name:String,age:Int->
        "我就是匿名函数，我的name：$name ,age:$age"
    }
}