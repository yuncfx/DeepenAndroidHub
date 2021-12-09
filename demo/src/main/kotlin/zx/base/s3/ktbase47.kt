package zx.base.s3
//foreacch函数，需要好好理解，可有助于理解匿名函数，参数是函数的函数
fun main() {
    val  str = "SBSDHDDHDHDHHF"

    //分行打印字符串str
   // foreach是一个函数，函数的参数也是一个函数，类似下面的自定义的函数loginApi
    str.forEach { it->
        println("每个字符串$it")
    }

    //loginApi第三个参数是一个函数，可以提取到括号外面
  loginApi { msg: String, code: Int ->
        println("msg $msg ,code $code")

    }

    //aa是一个函数对象，fun loginApi((kotlin.String, kotlin.Int) -> kotlin.Unit): kotlin.Unit
    val aa = ::loginApi
    println(aa)
    aa{
            msg: String, code: Int ->
        println("msg $msg ,code $code")
    }
}
fun ssss(a :Int ,b:Int,c:Int): Unit {

}
fun test(a:Int):String{
    return "test"
}


//loginApi第三个参数是一个函数，类型是(String,Int)->Unit
fun loginApi(responseResulet:(String,Int)->Unit){

        responseResulet("login success",200)
    }