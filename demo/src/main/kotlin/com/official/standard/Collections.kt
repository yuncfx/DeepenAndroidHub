package com.official.standard

/*
    在 Kotlin 中，List 的默认实现是 ArrayList，您可以将其视为可调整大小的数组。

    Set 的默认实现——LinkedHashSet——保留了元素插入的顺序。 因此，依赖于顺序的函数，例如 first() 或 last()，
    在这些集合上返回可预测的结果。
    另一种实现 - HashSet - 没有说明元素顺序，因此在其上调用此类函数会返回不可预测的结果。 但是，HashSet 需要
    更少的内存来存储相同数量的元素。

    Map 的默认实现——LinkedHashMap——在迭代map时保留元素插入的顺序。 反过来，另一种实现 - HashMap - 没有说明元素顺序。

 */

fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
    // 将满足predicate条件的元素，保存到shortWords中
    this.filterTo(shortWords) { it.length <= maxLength }
    // throwing away the articles
    val articles = setOf("a", "A", "an", "An", "the", "The")
    shortWords -= articles
}

fun main() {
    val words = "A long time ago in a galaxy far far away".split(" ")
    val shortWords = mutableListOf<String>()
    words.getShortWordsTo(shortWords, 3)
    println(shortWords)
}