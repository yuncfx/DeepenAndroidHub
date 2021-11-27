package zx.base.s3

//kotlin 语言的split
fun main() {
 
    var name:String = "hello,world"

    val split:List<String> = name.split(",")

    println(split)

    //c++解构，kotlin也有解构


    val(a1,a2) = split
    println("v1$a1,v2$a2")


    val(v1,v2,v3) = split
    println("v1$v1,v2$v2,v3$v3")



}