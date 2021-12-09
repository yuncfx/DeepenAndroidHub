package zx.base.s3

fun main() {
    var sourcepsw:String  = "ABCDFDGHFJKEUHHEHR"
    println("原始 的密码是$sourcepsw")
    //此处第二个参数是一
    val newpsd = sourcepsw.replace(Regex("[ABC]")){
         when (it.value) {
            "A" -> "1"
            "B"->"2"
            "C"->"3"
            else ->it.value
        }}

    println("加密之后的密码是$newpsd")

    val originpsd = newpsd.replace(Regex("[123]"),{
       when(it.value){
           "1"->"A"
           "2"->"B"
           "3"->"C"
           else ->it.value

       }
    })

    println("解密之后的密码是$originpsd")



}