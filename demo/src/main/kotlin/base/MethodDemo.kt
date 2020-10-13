package base


//fun main() {
//    println("Constructing Derived(\"Hello\", \"world\")")
//    val d = Derived("Hello", "world")
//}


open class Base(val name: String) {
    init {
        println("Initializing Base")
        println("hehe:$hehe")
    }

    companion object {
        private const val hehe = 5
    }

    open val size: Int = name.length.also { println("Initializing size in Base: $it") }
}

class Derived(name: String, val lastName: String) : Base(name.capitalize().also { println("Argument for Base:$it") }) {
    init {
        println("Initializing Derived")
    }

    override val size: Int = (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

