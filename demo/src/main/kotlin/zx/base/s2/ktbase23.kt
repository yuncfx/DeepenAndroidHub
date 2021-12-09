package zx.base.s2

fun main() {
    val count = "Derry".count()
    println(count)

    //计算r字符的数量
    val count1 = "Derry".count {
        it == 'r'
    }

    println(count1)
}