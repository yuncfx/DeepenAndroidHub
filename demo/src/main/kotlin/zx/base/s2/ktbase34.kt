package zx.base.s2
//匿名函数与具名函数
fun main() {

    //    showPersonInfo最后一个参数是匿名函数("zhangsan",20,'男'){匿名函数
    showPersonInfo("zhangsan",20,'男'){
        println(it)
    }

    //具名函数：：
    val kFunction1 = ::printa
    showPersonInfo("lisi",30,'女',kFunction1)
}

fun printa(str:String):Unit{
    println(str)
}



inline fun showPersonInfo(name:String,age:Int,sex:Char,showResult:(String)->Unit){
    val str = "name:$name,age:$age,sex:$sex"
    showResult(str)
}