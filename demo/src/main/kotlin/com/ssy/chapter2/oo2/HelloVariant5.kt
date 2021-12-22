package com.ssy.chapter2.oo2

// 泛型函数
fun <T> getValue(item: T): T {
    return item
}

fun main(args: Array<String>) {
    val item = getValue<Int>(3)
    println(item)

    val item2 = getValue("hello")
    println(item2)

    val upperBounds = UpperBoundsClass<ArrayList<String>>()

    val upperBounds2 = UpperBoundsClass2<ArrayList<String>>()

//    val upperBounds3 = UpperBoundsClass3<ArrayList<Person333<String>>>()

    val upperBoundsClass4 = UpperBoundsClass4<String>()
}

class UpperBoundsClass<T : List<String>> {

}

class UpperBoundsClass2<T : List<Any>> {

}

// TODO ? 怎么初始化
class UpperBoundsClass3<T : List<T>> {

}

class Person333<T>(override val size: Int) : List<T> {
    override fun contains(element: T): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(index: Int): T {
        TODO("Not yet implemented")
    }

    override fun indexOf(element: T): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<T> {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: T): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<T> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<T> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        TODO("Not yet implemented")
    }

}

// where关键字，表示T同时是Comparable<T>和Any的子类
class UpperBoundsClass4<T> where T : Comparable<T>, T : Any {

}