package zx.base.s2

fun main() {

    //loginApi第三个参数是一个函数，可以提取到括号外面
    loginApi("derry","123456"){msg:String,code:Int->
        println("msg $msg ,code $code")

    }

}
//loginApi第三个参数是一个函数，类型是(String,Int)->Unit
fun loginApi(username:String ,password:String,responseResulet:(String,Int)->Unit){
    if (username == "derry" && password=="123456"){
        responseResulet("login success",200)
    }else{
        responseResulet("login fail",404)
    }}