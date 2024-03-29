@file:PublicAccount("鱿鱼游戏")

package com.ssy.deepen

import com.ssy.PublicAccount

val list = arrayListOf(1, 5, 3, 2, 4)

/*
    Kotlin中sort函数，它有两种：
    1. sort()：对一个可变集合进行排序，返回Unit
    2. sorted()：排序后返回集合
 */
val sortList = list.sort()
val sortedList = list.sorted()

open class C {
    open fun sum(x: Int = 1, y: Int = 2): Int = x + y
}

class D : C() {
    // 注意，此处参数是y,x 而不是 x, y
    override fun sum(y: Int, x: Int): Int = super.sum(x, y) * 10
}

/*
    Parent和Children生成的Java类如下：
    As you can see, to get a we use getA method which references a. The only problem is that it is
    overwritten in Child so it actually references a from Child which is not set yet at this point.
    It is because parent is always initialized first.

    public static class Parent {

        private final String a;

        public String getA() {
            return this.a;
        }

        Parent(String a) {
            super();
            this.a = a;
            System.out.print(this.getA());
        }
    }

    public static final class Children extends Parent {
        private final String a;

        public String getA() {
            return this.a;
        }

        Children(String a) {
            super(a);
            this.a = a;
        }
    }
 */
open class Parent(open val a: String) {
    init {
        // warning: Accessing non-final property a in constructor
        println(a)
    }
}

class Children(override val a: String) : Parent(a)

open class Node(val name: String) {
    fun lookup() = "lookup in: $name"
}

class Example : Node("container") {
    fun createChild(name: String): Node? = Node(name)
    val child1 = createChild("child1")?.apply {
        println("child1 ${lookup()}")
    }

    val child2 = createChild("child2").apply {
        println("child2 ${lookup()}")
    }
}

/*
    out协变，往外输出，是生产者， Producer, 上界定义为T，可以将T的子类型当做T读出来
    Wrapper<Any>就是Wrapper<Nothing>的父类型，读出来的一定是Any
    类比Java中的 ? extends T
 */
class Wrapper<out T>

/*
    in逆变，往里写入，是消费者，Consumer，下界定义为T，可以将T往里写入，因为T是这个类型的子类型。
    为什么可以把Wrapper2<Any>赋值给Wrapper2<Nothing>呢，Any是Nothing的父类型。
    类比Java中的 ? super T
 */
class Wrapper2<in T>

// val instanceVariableOne: Wrapper<Nothing> = Wrapper<Any>() // won't compile
// Nothing是Any的子类型
val instanceVariableTwo: Wrapper<Any> = Wrapper<Nothing>()

val instanceVariableThree: Wrapper2<Nothing> = Wrapper2<Any>()
//val instanceVariableFour: Wrapper2<Any> = Wrapper2<Nothing>() // won't compile

enum class Color {
    Red, Green, Blue;

    companion object
}

fun Color.from(s: String) = when (s) {
    "#FF0000" -> Color.Red
    "#00FF00" -> Color.Green
    "#0000FF" -> Color.Blue
    else -> null
}

fun Color.Companion.from2(s: String) = when (s) {
    "#FF0000" -> Color.Red
    "#00FF00" -> Color.Green
    "#0000FF" -> Color.Blue
    else -> null
}

operator fun String.invoke(x: () -> String) = this + x()
fun String.z() = "!$this"
fun String.toString() = "$this!"

class Lazy {
    var x = 0
    val y by lazy { 1 / x }

    /*
        Lazy delegate可以被多次调用，直到它真正返回一个值为止，所以抛出异常后，
        x的值修改了，y可以被赋值，从而print出来。
     */
    fun hello() {
        try {
            println(y)
        } catch (e: Exception) {
            x = 1
            println(y)
        }
    }
}

typealias L = (String) -> Unit

fun foo(one: L = {}, two: L = {}) {
    one("one")
    two("two")
}

fun main() {
    println(sortList) // kotlin.Unit
    println(sortedList) // [1, 2, 3, 4, 5]
    println("*** puzzle 1 end ***")

    // 集合的相等判断使用的是引用判断，所以两个不同的list，不会相等，sequence也一样，判断的是引用地址。
    println(listOf(1, 2, 3) == listOf(1, 2, 3)) // true
    println(listOf(1, 2, 3).asSequence() == listOf(1, 2, 3).asSequence()) // false
    println(sequenceOf(1, 2, 3) == sequenceOf(1, 2, 3)) // false
    println("*** puzzle 2 end ***")

    val d: D = D()
    val c: C = d
    /*
        open functions的多态的，其类型由jvm虚拟机在运行时决定。
        具名函数是静态的，在编译期就固定了。
     */
    println(c.sum(x = 0)) // 20
    println(",")
    println(d.sum(x = 0)) // 10
    println("*** puzzle 3 end ***")

    /*
        可以看见，Parent中的a，在Child中被重写了，所以它实际上引用了Child中的a，
        而这个a在此时还没有被设置，因为父类总是先被初始化。所以，在使用Kotlin的简化构造函数时，一定要注意属性的覆写。
     */
    Children("abc") // print null, 需要结合生成的Java字节码来分析
    println("*** puzzle 4 end ***")

    /*
        由于createChild返回nullable，所以在child2的apply中，我们收到的context是Node?。
        我们不能在没有unpack的情况下直接调用lookup。如果我们想这样做，我们应该使用this?.lookup()。
        由于我们没有这样做，编译器会搜索它可以使用的lookup，并在Example上下文中找到它的实现。

        child1 lookup in: child1
        child2 lookup in: container
     */
    Example()
    println("*** puzzle 5 end ***")

    /*
        在这两种情况下，我们在Int类型上使用unaryMinus操作。当你输入-1时，它与1.unaryMinus()相同。
        这就是为什么1 + -(1)能正确工作。-1.inc()返回-2，因为inc用在了运算符之前。
        这个表达式等同于1.inc().unaryMinus()。为了解决这个问题，你应该使用小括号(-1).inc()。
     */
    println(-1.inc())  // -2
    println(",")
    println(1 + -(1)) // 0
    println("*** puzzle 6 end ***")

    /*
    前缀运算符++（++j）增加数字并返回新值，后缀运算符也增加属性，但返回前值。
    但会令人疑惑的部分是，前缀和后缀都是对Kotlin函数inc的引用，你从ide中点击++i和i++，
    都会跳到inc的引用，inc返回了一个新值，但是未被赋值。
     */
    var i = 0
    println(i.inc()) // 1
    println(i.inc()) // 1
    var j = 0
    println(j++) // 0
    println(++j) // 2
    println("*** puzzle 7 end ***")

    /*
        标签在这里毫无作用，不要被它迷惑了。
     */
    val wtf = wtf@ { n: Int -> wtf@ (wtf@ n + wtf@ 2) }(10)
    println(wtf) // 12
    println("*** puzzle 8 end ***")

    /*
        Elvis operator的优先级比+低
     */
    val x: Int? = 2
    val y: Int = 3
    val sum = x ?: 0 + y
    println(sum)
    println("*** puzzle 9 end ***")

    // not compile, 扩展方法只能由实例使用。
    //Color.from("#00FF00")
    // 这其实是调用了伴生对象的扩展方法
    Color.from2("#00FF00")
    println("*** puzzle 10 end ***")

    /*
        这道题重点是理清"x"{"y"}.z()，去掉z()，实际上就是重载的invoke函数，
        所以等价于String{}，{}就是invoke的参数。
        这里的扩展方法toString()不会使用，会被String自带的toString()覆盖掉。
     */
    println("x"{ "y" }.z())
    println("*** puzzle 10 end ***")

    Lazy().hello()
    println("*** puzzle 11 end ***")

    /*
    这道题搞清楚了，lambda就算是真的搞清楚了，foo {}，代表的是lambda省略()的写法，
    {}实际上是foo的最后一个参数，而foo()，括号中的内容，实际上是foo中按顺序的第一个参数。
    • 这对DSL来说是非常好的，可以通过Kotlin完成各种DSL的写法。
    • 但是当与默认参数结合在一起时，可能会引起混淆，不要把许多lambda作为参数，如果你仍然这样做，要避免使用默认值。
     */
    foo { println(it) } // two
    foo({ println(it) }) // one

}