package com.ssy.chapter4

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/*
 * p25
 * 关于属性委托的要求；
 * 只读属性（val修饰的属性）：委托需要提供一个名为getValue的方法，该方法接收如下参数：
 * - thisRef，需要是属性拥有者的类型或是其父类型（如果是扩展属性，这个类型指的是被扩展的那个类型）
 * - property，需要是KProperty<*>类型或是其父类型
 * getValue方法需要返回与属性相同的类型或是其子类型
 *
 * 可变属性（var修饰的属性），委托需要提供一个名为setValue的方法，该方法接收如下参数：
 * - thisRef，与getValue方法的thisRef要求一致
 * - property，与getValue方法property要求一致
 * - newValue，需要与属性的类型相同或是父类型
 *
 * getValue与setValue方法既可以作为委托类的成员方法实现，也可以作为其扩展方法来实现。
 * 这两个方法都必须要标记为operator关键字。委托类可以实现ReadOnlyProperty或是ReadWriteProperty接口，
 * 这些接口包含了对应的getValue和setValue方法，也可以不实现这两个接口，而是自己定义getValue与setValue方法。
 *
 * 委托转换规则：
 * 对于每个委托属性，Kotlin编译器在底层会生成一个辅助的属性，然后将对原有属性的访问委托给这个辅助属性。
 * 如属性prop，Kotlin编译器生成的隐含的属性名为prop$delegate,然后对原有的prop属性的访问器的访问都委托给这
 * 个prop$delegate属性。
 */

/*
  限制委托逻辑，提供一种“不是什么都能委托”的逻辑，能起来拦截委托的作用。
  提供委托(providing a delegate)
  通过定义provideDelegate operator，我们可以扩展委托的创建逻辑过程。如果对象定义了provideDelegate方法，那么
  该方法就会被调用来创建属性委托实例。
 */

class People {
    val name: String by PeopleLauncher()
    val address: String by PeopleLauncher()
}

class PropertyDelegate : ReadOnlyProperty<People, String> {
    override fun getValue(thisRef: People, property: KProperty<*>): String {
        return "hello world"
    }
}

// 提供委托对象，目的是为了过滤委托或装饰下委托
/*
    假设MyDelegate类提供了provideDelegate()方法， 则编译器大概生成的代码如下：
    class C {
        var prop: Type by MyDelegate()
    }

    // this code is generated by the compiler
    // when the 'provideDelegate' function is available:
    class C {
        // calling "provideDelegate" to create the additional "delegate" property
        private val prop$delegate = MyDelegate().provideDelegate(this, this::prop)
        var prop: Type
            get() = prop$delegate.getValue(this, this::prop)
            set(value: Type) = prop$delegate.setValue(this, this::prop, value)
    }

    请注意， provideDelegate 方法仅影响辅助属性的创建，不影响为 getter 或 setter 生成的代码。
 */
class PeopleLauncher {
    /**
     * thisRef 和 property跟 getValue() | setValue()里的规则一样,
     */
    operator fun provideDelegate(
        thisRef: People,
        property: KProperty<*>
    ): ReadOnlyProperty<People, String> {
        println("welcome")

        when (property.name) {
            "name", "address" -> return PropertyDelegate()
            else -> throw Exception("not valid name")
        }
    }
}

/**
 * 使用标准库中的 PropertyDelegateProvider 接口，您可以在不创建新类的情况下创建委托提供程序。
 */
val provider = PropertyDelegateProvider { thisRef: Any?, property ->
    ReadOnlyProperty<Any?, Int> { thisRef, property ->
        println("print provider")
        42
    }
}

val delegate:Int by provider

fun main() {
    val people = People()
    println(people.name)
    println(people.address)

    println(delegate)
}
