package design.pattern.lg

/**
 * 建造者模式 demo
 */

internal class Product(private val partA: Int, private val partB: String?, private val partC: Int) {
    override fun toString(): String {
        return "Product{" +
                "partA=" + partA +
                ", partB='" + partB + '\'' +
                ", partC=" + partC +
                '}'
    }
}

internal interface Builder {
    fun buildPartA(partA: Int)
    fun buildPartB(partB: String?)
    fun buildPartC(partC: Int)
    val result: Product
}

internal class ConcreteBuilder : Builder {
    private var partA = 0
    private var partB: String? = null
    private var partC = 0
    override fun buildPartA(partA: Int) {
        this.partA = partA
    }

    override fun buildPartB(partB: String?) {
        this.partB = partB
    }

    override fun buildPartC(partC: Int) {
        this.partC = partC
    }

    override val result: Product
        get() = Product(partA, partB, partC)
}

internal class Director {
    fun construct(builder: Builder) {
        builder.buildPartA(1)
        builder.buildPartB("test-test")
        builder.buildPartC(2)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val director = Director()
            val builder: Builder = ConcreteBuilder()
            director.construct(builder)
            println(builder.result)
        }
    }
}

/**
 * 将自身类作为建造者的方法来实现。
 */
internal class MigrantWorker private constructor() {
    //所有属性
    private var name: String? = null
    private var age = 0
    private var phone: String? = null
    private var gender: String? = null

    //将属性作为步骤
    fun name(name: String?): MigrantWorker {
        this.name = name
        return this
    }

    //将属性作为步骤
    fun age(age: Int): MigrantWorker {
        this.age = age
        return this
    }

    //将属性作为步骤
    fun phone(phone: String?): MigrantWorker {
        this.phone = phone
        return this
    }

    //将属性作为步骤
    fun gender(gender: String?): MigrantWorker {
        this.gender = gender
        return this
    }

    //执行创建操作
    fun build(): MigrantWorker {
        validateObject(this)
        return this
    }

    private fun validateObject(migrantWorker: MigrantWorker) {
        //可以做基础预校验，或自定义校验
    }

    override fun toString(): String {
        return "MigrantWorker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                '}'
    }

    companion object {
        fun builder(): MigrantWorker {
            return MigrantWorker()
        }
    }
}