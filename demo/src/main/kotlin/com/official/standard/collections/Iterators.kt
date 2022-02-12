@file:Official

package com.official.standard.collections

import com.ssy.Official


val numbersIterator = numbers.iterator()
val listIterator = numbers.listIterator()
val mutableListIterator: MutableListIterator<Int> = mutableList.listIterator()

fun main(args: Array<String>) {
    while (numbersIterator.hasNext()) {
        println(numbersIterator.next())
    }

    for (item in numbers) {
        println(item)
    }

    numbers.forEach {
        println(it)
    }

    println("listIterator test---")
    while (listIterator.hasNext()) {
        listIterator.next()
    }
    while (listIterator.hasPrevious()) {
        print("Index:${listIterator.previousIndex()}")
        println(", value : ${listIterator.previous()}")
    }

    println("mutable iterators test---")
    mutableListIterator.next()
    mutableListIterator.remove()
    mutableListIterator.next()
    mutableListIterator.add(6)
    mutableListIterator.next()
    mutableListIterator.set(8)
    println(mutableList) // [2, 6, 8]
}