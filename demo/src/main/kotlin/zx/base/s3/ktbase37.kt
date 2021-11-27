package zx.base.s3

fun main() {

    var name:String?=""

    //如果name为空，？后面这一截不执行
    //name 是null，输出null
    //name是空串，输出default
    val r = name?.let {
        //如果执行到这里面，it一定不是null
        println("test")
        if (it.isBlank()) {
            "default"
        } else {
            it
        }
    }

    println(r)


}