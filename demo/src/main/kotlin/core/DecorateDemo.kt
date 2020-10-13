package core

interface MacBook {
    fun getCost(): Int
    fun getDesc(): String
    fun getProdDate(): String
}

class MacBookPro : MacBook {
    override fun getCost() = 10000
    override fun getDesc() = "MacBook Pro"
    override fun getProdDate() = "Late 2011"
}

// 装饰类
class ProcessorUpgradeMacBookPro(val macBook: MacBook) : MacBook by macBook {
    override fun getCost() = macBook.getCost() + 219
    override fun getDesc() = macBook.getDesc() + ", +1G Memory"
}

class Printer {
    fun drawLine() {
        println("————————")
    }
    fun drawDottedLine() {
        println("- - - - -")
    }
    fun drawStars() {
        println("＊＊＊＊＊＊＊＊")
    }
}

fun Printer.startDraw(decorated: Printer.() -> Unit) {
    println("+++ start drawing +++")
    this.decorated()
    println("+++ end drawing +++")
}

fun main() {
    val macBookPro = MacBookPro()
    val processorUpgradeMacBookPro = ProcessorUpgradeMacBookPro(macBookPro)
    println(processorUpgradeMacBookPro.getCost())
    println(processorUpgradeMacBookPro.getDesc())

    println("----------divide line ----------")

    Printer().run {
        startDraw {
            drawLine()
        }

        startDraw {
            drawDottedLine()
        }

        startDraw {
            drawStars()
        }
    }

}

