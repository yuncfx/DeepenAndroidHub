package zx.base

fun main() {
method2("张三",7)
}

//函数默认都是public
//其实kotlin的函数，更规范，先有输入，再有输入
fun method1(age: Int): Int {
return 200
}

//默认值
fun method2(name: String="王五", age: Int = 5): Unit {
    println("姓名是${name},年龄${age}")

}

fun method3() :Unit {
    return println("sss")
}