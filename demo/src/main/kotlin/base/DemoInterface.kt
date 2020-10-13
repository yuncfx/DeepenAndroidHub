package base

class DemoInterface {
    val interface1: Interface1 = object : Interface1 {
        override fun print() {
            println("in interface")
        }
    }
}

//fun main() {
//    DemoInterface().interface1.print()
//}

