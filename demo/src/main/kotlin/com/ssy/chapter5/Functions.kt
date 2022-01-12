package com.ssy.chapter5

/*
    一等公民 functions, functions可以出现在任何其它属性能出现的地方
    You can perform any operations on functions that are possible for other non-function values.
 */

fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R) : R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}