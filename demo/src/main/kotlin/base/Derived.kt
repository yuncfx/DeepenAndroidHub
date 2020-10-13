package base

interface NewBase {
    fun printMessage()
    fun printMessageLine()
}

class NewBaseImpl(private val x: Int) : NewBase {
    override fun printMessage() {
        println(x)
    }

    override fun printMessageLine() {
        println(x)
    }
}

class NewDerived(b:NewBase) : NewBase by b {
    override fun printMessage() {
        print("abc")
    }
}

//fun main() {
//    val b = NewBaseImpl(10)
//    NewDerived(b).printMessage()
//    NewDerived(b).printMessageLine()
//}


