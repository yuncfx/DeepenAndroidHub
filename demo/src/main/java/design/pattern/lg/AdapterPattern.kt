package design.pattern.lg

// 目标类或接口
interface TargetAbstraction {
    fun filter(str: String): String
}

// 具体实现类，未经过适配
class ConcreteImpl : TargetAbstraction {
    override fun filter(str: String): String {
        return str + "hello"
    }
}

// 对象适配
class Adapter : TargetAbstraction {
    val adaptee: Adaptee = Adaptee()

    override fun filter(str: String): String {
        adaptee.check(str)
        return adaptee.replace(str)
    }
}

// 类适配
class Adapter2 : Adaptee(), TargetAbstraction {
    override fun filter(str: String): String {
        return replace(str) + ", hello"
    }
}

// 已存在的、具有特殊功能、但不符合我们既有的标准接口的类
open class Adaptee {
    fun check(str: String) {

    }

    fun replace(str: String): String {
        return "str$str"
    }
}