package grammer

val staticVariable = "hehe"

class DemoClass2(name: String, age: Int) : Base(name, age) {

}

data class DataClass(val name: String, val age: Int, val sex: Int) {}

sealed class SealedClass {
    data class Constant(var name: String) : SealedClass()
    data class Constant2(var name: String) : SealedClass()
}

class DemoClass(override var name: String, override var age: Int) : Base(name, age) {

    private var sex = 0

    constructor(name: String, age: Int, sex: Int) : this(name, age) {
        this.sex = sex
    }

    init {
        name = "lisi"
        age = 15
    }

    companion object {
        val Obj = DemoClass("zhangsan", 11)
        val Obj2 = DemoClass("zhangsan", 12, 0)

        fun staticMethod() {
            println("static method")
        }
    }

    override fun toString(): String {
        return "name:" + "$name, age:" + "$age" + ", sex:$sex"
    }
}

object StaticClass {

}

fun main() {
    println(DemoClass.Obj)
    println(DemoClass.Obj.javaClass)
    DemoClass.staticMethod()
    println(StaticClass.javaClass)

    println(staticVariable)

    DataClass("zhangsan", 10, 1).age
    val sealedClass: SealedClass = SealedClass.Constant("zhangsan")
    when (sealedClass) {
        is SealedClass.Constant -> println(sealedClass)
        is SealedClass.Constant2 -> println(sealedClass)
    }
}