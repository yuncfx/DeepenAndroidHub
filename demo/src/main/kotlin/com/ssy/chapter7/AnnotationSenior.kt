@file: JvmName("AnnotationSenior")

package com.ssy.chapter7

import kotlin.reflect.KClass

// 注解也可以拥有自己的构造方法，并且构造方法也可以接收参数

/*
    注解构造方法所允许的参数类型：
    与Java原生类型所对应的类型（Int, Long ...)
    字符串：String
    classes（MyClass::class)
    枚举：enums
    其它的注解
    上述类型的数组类型

    Kotlin的注解参数是不允许为可空类型的，因为JVM不支持以null的形式存储注解属性值的
    如果某个注解被用作其它注解的参数，那么其名字就不需要以@字符开头
 */

annotation class MyAnnotation2(val str: String)

@MyAnnotation2("hello")
class MyClass4

annotation class MyAnnotation3(val str: String, val myAnnotation: MyAnnotation2)

@MyAnnotation3("test", MyAnnotation2("welcome"))
class MyClass5

/*
    如果需要将某个class作为注解的参数，那么请使用Kotlin class（KClass）
    Kotlin编译器会自动将其转换为Java Class
    这样，Java代码就可以正常看到注解与参数了。
 */
annotation class MyAnnotation4(val arg1: KClass<*>, val arg2: KClass<out Any>)

@MyAnnotation4(String::class, Int::class)
class MyClass6

// 注解使用处目标 (use-site target)
/*
    在对类的属性或是主构造方法的参数声明注解时，会存在多个Java元素都可以通过对应的Kotlin元素生成出来，
    因此，在所生成的Java字节码中，就会存在多个可能的位置来生成相应的注解。
    若想精确指定如何来生成注解，那么可以使用注解的使用处目标方式来实现

    支持使用处目标的列表如下：
    1. file
    2. property  (annotations with this target are not visible to Java)
    3. field
    4. get (property getter)
    5. set (property setter)
    6. receiver (receiver parameter of an extension function or property)
    7. param (construct parameter)
    8. setparam (property setter parameter)
    9. delegate (the field storing the delegate instance for a delegated property)
 */
class MyClass7(
    @field: MyAnnotation val arg1: String, // 注解Java Field
    @get: MyAnnotation val arg2: String, // 注解 Java getter
    @param: MyAnnotation val arg3: String // 注解 Java构造方法参数
)

fun @receiver:MyAnnotation String.myExtension() {
    println("myExtension")
}

class Example {
    // 如果同一个target有多个注解，可以将它们放在一个中括号中
    @set:[Suspendable MyAnnotation2("test")]
    var clazz: MyClass7? = null
}

// The compiler generates the @Tag.Container containing annotation
@Repeatable
annotation class Tag(val name: String)

// 强行指定Container的名字为Tags
@JvmRepeatable(Tags::class)
annotation class Tag2(val name: String)
annotation class Tags(val value: Array<Tag2>)

/*
    SinceKotlin("1.6")
    在 Java 中，注解类型是接口的一种形式，因此您可以实现它并使用实例。 作为这种机制的替代方案，Kotlin 允许您在
    任意代码中调用注解类的构造函数，并类似地使用生成的实例。
 */
annotation class InfoMarker(val info: String)

fun processInfo(marker: InfoMarker): Unit = TODO()

@SinceKotlin("1.6")
fun main(args: Array<String>) {
    if (args.isNotEmpty())
//        processInfo(getAnnotationReflective(args))
    else
        processInfo(InfoMarker("default"))
}