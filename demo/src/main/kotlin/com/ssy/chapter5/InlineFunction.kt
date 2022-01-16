@file:Official

package com.ssy.chapter5

import com.ssy.Official
import java.lang.StringBuilder
import java.util.concurrent.locks.Lock

/*
 * 内联函数 (inline functions)
 * 优点：将函数体直接移动到调用处，减少了函数调用的消耗
 * 缺点：导致字节码文件变大，在合理的使用情况下（避免内联大函数），在性能方面带来的好处（尤其是在循环内部调用高阶函数），可以抵消这个副作用。
 *
 * 内联函数出现的原因：
 * 高阶函数的使用会产生一些运行时副作用，每个函数都是一个对象，并且持有一个闭包。
 * 闭包是一个变量的作用域，该作用域内变量可以在函数体内被访问。内存分配（包括函数对象和类）和虚拟调用
 * 都会产生运行时开销。
 *
 * 运行时开销，可以使用inline内联来消除。
 * inline fun <T> lock(lock: Lock, body: () -> T): T {...}
 * 如下的调用：
 * lock(l) {foo()}
 * 内联之后如下：
 * l.lock()
 * try {
 *   foo()
 * } finally {
 *   l.unlock()
 * }
 *
 */


/*
    如果一个内联函数没有内联参数，并且没有reified类型参数，编译器会给出警告，表明这个内联是没有必要的。
    可以加如下的注解抑制警告
 */
@Suppress("NOTHING_TO_INLINE")
inline fun myCalculate(a: Int, b: Int) = a + b

// inline作用于函数本身和lambda参数，这些都会被内联到调用侧。
inline fun <reified T> lock(lock: Lock, body: () -> T): Unit {
}

// 如果想要某个lambda参数不被内联，可以使用noinline关键字修饰
/*
    inline lambda表达式只能在inline函数内调用或作为inline参数被传递。
    而noinline lambda表达式不受此限制，可以随意使用。
 */
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {}

/*
 * non-local returns, 非局部返回：
 * 实际上表示的是在一个方法内部，我们可以在其中通过一个lambda表达式的返回来直接将外层方法返回。
 */
fun ordinaryFunction(block: () -> Unit) {
    println("hi")
    block.invoke()
}

fun foo() {
    ordinaryFunction {
        //return 不能直接使用return，lambda不能让封闭函数返回
        return@ordinaryFunction
    }

    // ordinaryFunction局部返回，仍然会打印这句文字
    println("hello ordinary")

    inlined {
//        return@inlined // 内联lambda表达式仍然可以返回带标签
        return // OK, inline lambda表达式可以直接返回（也就是非局部返回）
    }

    // 以下这行文字不会输出，因为方法foo已经返回了。
    println("hello inlined")
}

inline fun inlined(block: () -> Unit) {
    println("inlined hi")
    block.invoke()
}

// 局部返回
fun hasZeros(ints: List<Int>): Boolean {
    ints.forEach {
        if (it == 0) return true // return from hasZeros
    }
    return false
}

/*  有些内联函数可能不直接调用传入的lambda参数，而是在另一个执行上下文如 局部object或嵌套函数。
    这种情况下，lambda内的非局部返回是不被允许的。需要加关键字crossinline修饰。
    crossinline的作用实际上就是表示被标记的lambda表达式是不允许非局部返回的。

    break & continue还未得到支持，不允许在lambda中使用
 */
inline fun f(crossinline body: () -> Unit) {
    val f = Runnable { body() }
}

abstract class TreeNode {
    var parent: TreeNode? = null
}

class MyTreeNode : TreeNode() {

}

/*
    使用普通T参数，该方法等价于下面的[findParentOfType()]方法，但是不太优雅。
    调用方式如下：
    treeNode.findParentOfType(MyTreeNode::class.java)
 */
fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    return p as T?
}

/*
    Reified type parameters, 使用reified关键字具体化类型T，因此T在方法内可以被访问，甚至可以看作为一个普通类。
    不需要借助反射，并且!is 和 as 这种关键字都可以使用。
    使用方法如下 myTree.findParentOfType<MyTreeNode>()

    普通非内联函数不能有reified参数。一个没有运行时表示的类型如（Nothing，非具体化的类型参数），不能传递给reified
    参数使用。
 */
inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}

inline fun <reified T> membersOf() = T::class.members

/*
    内联属性(inline property)。
    没有支撑字段的属性，可以使用inline修饰。作用和内联函数一致。
 */
class Foo

val foo: Foo
    inline get() = Foo()

var t: Foo? = null
var foo2: Foo
    get() = t ?: Foo()
    inline set(v) {
        t = v
    }

inline var foo3: Foo
    get() = t ?: Foo()
    set(v) {
        t = v
    }

// Inline property cannot have backing field
/*
var foo3: Foo
    inline set(V) {
        field = Foo()
    }
*/

/*
    todo Restrictions for public API inline functions
    内部声明可以使用 @PublishedApi 进行注释，这允许它在公共 API 内联函数中使用。
    当内部内联函数被标记为@PublishedApi 时，它的主体也会被检查，就好像它是公共的一样。
 */

fun main(args: Array<String>) {
    println(myCalculate(1, 2))

    foo()

    val treeNode = MyTreeNode()
    treeNode.findParentOfType(MyTreeNode::class.java)
    treeNode.findParentOfType<MyTreeNode>()
    membersOf<StringBuilder>().joinToString("\n")
}