package zx.base.s2

fun main() {

    //
    loginApi("derry","123456"){msg:String,code:Int->
        println("msg $msg ,code $code")

    }

}

fun loginApi(username:String ,password:String,responseResulet:(String,Int)->Unit){
    if (username == "derry" && password=="123456"){
        responseResulet("login success",200)
    }else{
        responseResulet("login fail",404)
    }}