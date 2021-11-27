package zx.base.s2

fun main() {
    //匿名函数参数
    val methodAction :(Int,Int,Int)->String = {aa,bb,cc->


        "derry + parameter $aa "
    }

    println(methodAction(1,2,1))


    //it 关键字
    //一个参数的话直接可以用it关键字
    val methodAction2 :(Int)->String = {
        "$it"
    }

    println(methodAction2(5))

    //类型推断
    //方法名： 必须指定参数类型和返回类型
    //方法名= 类型推断返回类型
    val mehtod1 = {n1:Int,n2:Int,n3:Float->
        "jjs"
    }

    println(mehtod1(1,2,1.0f))
    println(mehtod1)


    val method2 = {
        5555  //无参数，返回int
    }

    println(method2())

    val mehtod3 = {number:Int ->
        number

    }
    println(mehtod3(9))
    println(mehtod3)






}

fun ssss(a :Int ,b:Int,c:Int): Unit {

}