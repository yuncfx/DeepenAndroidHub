package design.pattern.lg

/**
 * 抽象工厂模式 demo
 */

internal open class Chair
internal open class Sofa
internal open class Table
internal class ChinaChair : Chair()
internal class ChinaSofa : Sofa()
internal class ChinaTable : Table()
internal class USAChair : Chair()
internal class USASofa : Sofa()
internal class USATable : Table()
internal class Client(af: AbstractFactory) {
    private val myChair: Chair
    private val mySofa: Sofa
    private val myTable: Table

    //通过抽象工厂来生产家具
    init {
        myChair = af.createChair()
        mySofa = af.createSofa()
        myTable = af.createTable()
    }
} //抽象的家具工厂

internal abstract class AbstractFactory {
    abstract fun createChair(): Chair
    abstract fun createSofa(): Sofa
    abstract fun createTable(): Table
} //中国的家具工厂

internal class ChinaFactory : AbstractFactory() {
    override fun createChair(): Chair {
        return ChinaChair()
    }

    override fun createSofa(): Sofa {
        return ChinaSofa()
    }

    override fun createTable(): Table {
        return ChinaTable()
    }
} //美国的家具工厂

internal class USAFactory : AbstractFactory() {
    override fun createChair(): Chair {
        return USAChair()
    }

    override fun createSofa(): Sofa {
        return USASofa()
    }

    override fun createTable(): Table {
        return USATable()
    }
}