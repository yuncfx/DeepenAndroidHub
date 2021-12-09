package zx.base.s3
//kotlin的==和===
fun main() {
    val name1 = "Derry"
    val name2 = "Derry"

    //这里是判断name1和name2是否equlas
    println(name1 == name2)
    //这里是判断name1和name2引用是否相等，因为java的字符串常量池，name1和name2的引用是一样的
    println(name1 === name2)


    val name4 = "derry".capitalize()//修改成“Derry”

    println(name1 == name4)
    //这里是判断name1和name4引用是否相等，，name1和name4的引用是不一样的
    println(name1 === name4)
}