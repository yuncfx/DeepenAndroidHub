package design.pattern.lg

/**
 * Bridge Pattern
 * 桥接模式demo，属于结构型设计模式
 */
//实体与行为的关联
abstract class AbstractEntity(behavior: AbstractBehavior) {
    abstract var field: Any?
    abstract fun request()
    var behavior: AbstractBehavior? = behavior
}

abstract class AbstractBehavior {
    abstract fun operation()
}

class DetailEntityA(behavior: AbstractBehavior) : AbstractEntity(behavior) {
    override var field: Any? = null
    override fun request() {
        super.behavior?.operation()
    }
}

class DetailEntityB(behavior: AbstractBehavior) : AbstractEntity(behavior) {
    override var field: Any? = null
    override fun request() {
        super.behavior?.operation()
    }
}

class DetailBehaviorA : AbstractBehavior() {
    override fun operation() {
        println("op from DetailBehaviorA")
    }
}

class DetailBehaviorB : AbstractBehavior() {
    override fun operation() {
        println("op-1 from DetailBehaviorB")
    }
}

class Client1 {
    var entity: AbstractEntity? = null
}

fun main(args: Array<String>) {
    val entity: AbstractEntity = DetailEntityA(DetailBehaviorA())
    entity.request()
}

