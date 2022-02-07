package grammer

import org.junit.Test
import java.math.BigDecimal

fun main() {
    test()
}

@Test
fun test() {
    var c: BigDecimal = BigDecimal(1020049)

    var array: IntArray = intArrayOf(1, 2, 3)
    println(array.toString())

    var array2: Array<Int> = arrayOf(1, 2, 3, 4)

    var array3 = Array(5) { i -> i * 5 }


    val arr = Array(3) { IntArray(5) { i -> i * 15 } }//三个长度为5的Int数组的二维数组
    print(arr[1][1])
    for (one in arr) {
        println()
        for (two in one) {
            print("$two ")
        }
    }

    println()

    for (i in array3.indices) {
        print(array3[i].toString() + " ")
    }


}