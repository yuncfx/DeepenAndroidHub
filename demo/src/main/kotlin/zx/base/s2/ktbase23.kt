package zx.base.s2

fun main() {
    val count = "Derry".count()
    println(count)

    val count1 = "Derry".count {
        it == 'r'
    }

    println(count1)
}