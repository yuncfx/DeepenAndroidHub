package zx.base.s2

fun main() {

    //两个冒号把普通函数变成了函数类型的对象
    login("derry","123456",::methodResult)
}

fun methodResult(msg:String,code:Int){
    println("msg:$msg,code:$code")
}

inline fun login(name:String,password:String ,resule:(String,Int)->Unit){
    if(name == "derry"&& password == "123456"){
        resule("login success",200)
    }else{
        resule("login fail",404)
    }
}