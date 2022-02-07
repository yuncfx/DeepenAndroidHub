package com.official

import java.security.Provider

/**
Kotlin属性在Java中的生成规则；
一个 getter 方法，其名称是通过添加 get 前缀来得出的
一个 setter 方法，通过在 set 前缀前面计算名称（仅适用于 var 属性）
一个私有字段，与属性名称同名（仅适用于具有支持字段的属性）

private String firstName;

public String getFirstName() {
return firstName;
}

public void setFirstName(String firstName) {
this.firstName = firstName;
}

如果属性名称以is开头，则使用不同的名称映射规则：getter的名称将与属性名称相同，setter的名称将is替换为set。
例如，对于一个属性 isOpen，getter 将被称为 isOpen()，而 setter 将被称为 setOpen()。 此规则适用于任何类
型的属性，而不仅仅是布尔值。
 */

/*
  在包 org.example 内的文件 app.kt 中声明的所有函数和属性，包括扩展函数，都被编译为名为 org.example.AppKt
  的 Java 类的静态方法。
 */

/*
    如果您需要在 Java 中将 Kotlin 属性公开为字段，请使用 @JvmField 注释对其进行注释。 该字段将具有与基础属性
    相同的可见性。 如果出现以下情况，您可以使用 @JvmField 注释属性：
    * 有一个支持领域
    * 非私有
    * 没有 open、override 或 const 修饰符
    * 不是委托属性

    Late-Initialized 属性也作为字段公开。 该字段的可见性将是与 lateinit 属性设置器的可见性相同。
 */
class User(id: String) {
    @JvmField
    val ID = id
}

/*
    静态字段
 */
class Key(val value: Int) {
    companion object {
        @JvmField
        val COMPARATOR: Comparator<Key> = compareBy<Key> { it.value }
    }
}

object Singleton {
    lateinit var provider: Provider
}

// file example.kt
object Obj {
    const val CONST = 1
}

class C {
    companion object {
        const val VERSION = 9

        @JvmStatic
        fun callStatic() {
        }

        fun callNonStatic() {}
    }
}

const val MAX = 239

/*
    从 Kotlin 1.3 开始，@JvmStatic 也适用于在接口的伴随对象中定义的函数。 此类函数编译为接口中的静态方法。 请注意，
    接口中的静态方法是在 Java 1.8 中引入的，因此请务必使用相应的目标。

    @JvmStatic 注释也可以应用于对象或伴随对象的属性，使其 getter 和 setter 方法成为该对象或包含伴随对象的类中的静态成员。
 */
interface ChatBot {
    companion object {
        @JvmStatic
        fun greet(username: String) {
            println("Hello, $username")
        }
    }
}

/*
    访问权限映射：
    1. private -> private
    2. private top level -> package-local
    3. protected -> protected (Java的protected访问权限更广)
    4. internal -> public（内部类的成员会经过名称修改，以便更难从 Java 中意外使用它们，并允许根据 Kotlin 规则
        重载具有相同签名但彼此看不到的成员）
    5. public -> public
 */

/*
    获取KClass:
    kotlin.jvm.JvmClassMappingKt.getKotlinClass(MainView.class)
 */


/*
    以下两个方法它们在 Kotlin 中具有相同的名称，我们可以使用 @JvmName 注释其中一个（或两个）并指定一个不同的名称作为参数：
 */
fun List<String>.filterValid(): List<String> {
    return arrayListOf()
}

@JvmName("filterValidInt")
fun List<Int>.filterValid(): List<Int> {
    return arrayListOf()
}

val x: Int
    @JvmName("getX_prop")
    get() = 15

fun getX() = 10

// 在没有显式实现 getter 和 setter 的情况下更改生成的属性访问器方法的名称
@get:JvmName("y")
@set:JvmName("changeY")
var y: Int = 23

/*
    Nothing 类型很特殊，因为它在 Java 中没有自然对应物。 事实上，每个 Java 引用类型，包括 java.lang.Void，
    都接受 null 作为值，而 Nothing 不接受。 因此，这种类型在 Java 世界中无法准确表示。 这就是 Kotlin 生成一个
    使用 Nothing 类型参数的原始类型的原因：
 */
fun emptyList(): List<Nothing> = listOf()
// is translated to
// List emptyList() { ... }

