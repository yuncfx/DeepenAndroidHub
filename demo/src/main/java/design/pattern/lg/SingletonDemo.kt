package design.pattern.lg

/*
    单例模式 demo

    两个角色 Client客户端， Singleton单例类
    实现要点：
    1. 构造方法不对外开放，一般是private
    2. 通过一个静态方法或枚举返回该单例对象
    3. 确保单例类对象有且只有一个，尤其是多线程情况下
    4. 确保单例类对象在反序列化时不会重新创建对象
 */

object Hungry {}

class LazyPattern private constructor() {

    companion object {
        private var ins: LazyPattern? = null
            get() {
                if (field == null) {
                    field = LazyPattern()
                }
                return field
            }

        // 需要标记为同步方法
        @Synchronized
        fun getInstance(): LazyPattern {
            return ins!!
        }
    }
}

enum class SingletonDemo {
    Instance;

    fun print() {

    }
}

// double check singleton
class SingletonDemo2 private constructor() {
    companion object {
        val instance: SingletonDemo2 by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SingletonDemo2()
        }
    }
}

// holder singleton pattern
class SingletonDemo3 private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        init {
            println("initialized!")
        }

        val holder = SingletonDemo3()
    }
}

class SingletonWithParameter private constructor(private val property: Int) {
    companion object {
        @Volatile private var instance: SingletonWithParameter? = null

        fun getInstance(property: Int) =
            instance ?: synchronized(this) {
                instance ?: SingletonWithParameter(property).also { instance = it }
            }
    }
}

fun main(args: Array<String>) {
    println(SingletonDemo3::class.simpleName)
}

