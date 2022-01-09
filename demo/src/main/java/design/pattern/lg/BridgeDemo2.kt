package design.pattern.lg

abstract class Coffee(impl: CoffeeAdditives) {
    var impl: CoffeeAdditives? = impl

    /*
        Sub classes really define coffee
     */
    abstract fun makeCoffee()
}

class LargeCoffee(impl: CoffeeAdditives) : Coffee(impl) {
    override fun makeCoffee() {
        println("Big $impl coffee")
    }
}

class SmallCoffee(impl: CoffeeAdditives) : Coffee(impl) {
    override fun makeCoffee() {
        println("Small $impl coffee")
    }
}

abstract class CoffeeAdditives {
    /*
        Sub classes define add something to coffee
     */
    abstract fun addSomething(): String
}

class Sugar : CoffeeAdditives() {
    override fun addSomething(): String {
        return "sugar added"
    }
}

class Ordinary : CoffeeAdditives() {
    override fun addSomething(): String {
        return "original"
    }
}

fun main() {
    // original
    val ordinary = Ordinary()
    // sugar
    val sugar = Sugar()

    val coffee: Coffee = LargeCoffee(ordinary)
    val coffee2: Coffee = SmallCoffee(ordinary)
    val coffee3: Coffee = LargeCoffee(sugar)
    val coffee4: Coffee = SmallCoffee(sugar)
    coffee.makeCoffee()
    coffee2.makeCoffee()
    coffee3.makeCoffee()
    coffee4.makeCoffee()
}