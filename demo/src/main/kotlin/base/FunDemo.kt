package base

fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) {}

//fun main() {
//    foo(1) {}
//
//    foo(qux = { println("Hello") })
//
//    foo { println("hello") }
//
//    foo2 {
//        println("1111111111")
//    }
//}

fun foo2(body:()->Unit) {
    ordinaryFunction {
        println("zc_test lambda exit")
//        return
    }
    println("zc_test ---> foo() end")
}

inline fun ordinaryFunction(block: () -> Unit) {
    println("haha")
    block.invoke()
    println("haha 2333")
}