package zx.base.s3

import kotlin.math.roundToInt
import kotlin.reflect.typeOf

//数字类型的安全转换函数
//double和int的转换
fun main() {

    val num1:Int = "666".toInt()
    //此处会崩溃 java.lang.NumberFormatExceptio
//    val num2:Int = "666.6".toInt()

    //num3 声明为可空的类型
    val num3:Int?= "666.6".toIntOrNull()

    println("num3:"+num3?:"原来你是null")

    val num4:Int?= "666".toIntOrNull()

    println("num4:"+ num4?:"原来你是null")


    println(65.455466.toInt())//65 toInt舍弃小数位
    println(65.455466.roundToInt())//65 roundToInt 四舍五入
    println(65.56665.toInt())//65
    println(65.56665.roundToInt())//66

    //a 是string 类型，保留了三位小数点，且四舍五入
   val a =  "%.3f".format(65.748888)
    println(a)



}