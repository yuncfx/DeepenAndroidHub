package com.official.standard

/*
    除了集合之外，Kotlin 标准库还包含另一种容器类型——序列 (Sequence<T>)。序列提供与 Iterable 相同的功能，但
    实现了另一种多步骤集合处理方法。

    当 Iterable 的处理包括多个步骤时，它们会急切地执行：每个处理步骤都完成并返回其结果——一个中间集合。在此集合
    上执行接下来的步骤。反过来，序列的多步处理尽可能延迟执行：实际计算仅在请求整个处理链的结果时发生。

    操作执行的顺序也不同：Sequence 为每个元素一个接一个地执行所有处理步骤。反过来，Iterable 完成整个集合的每
    个步骤，然后继续下一步。

    因此，序列让您避免构建中间步骤的结果，从而提高整个收集处理链的性能。然而，序列的惰性增加了一些开销，这在处
    理较小的集合或进行更简单的计算时可能很重要。因此，您应该同时考虑 Sequence 和 Iterable 并决定哪一个更适
    合您的情况。
 */
val numberSequence = sequenceOf("four", "three", "two", "one")
fun main(args: Array<String>) {
    println(numberSequence.javaClass)
    println(numberSequence::class.java)

    val numbers = listOf("one", "two", "three", "four")
    val sequence = numbers.asSequence()
    println(sequence)

    // The sequence generation stops when the provided function returns null.
    // So, the sequence in the example below is infinite.
    val oddNumbers = generateSequence(1) { it + 2 }
    println(oddNumbers.take(5).toList())
    /*
        Exception in thread "main" java.lang.ArithmeticException: Count overflow has happened.
        at kotlin.collections.CollectionsKt__CollectionsKt.throwCountOverflow(Collections.kt:467)
        at kotlin.sequences.SequencesKt___SequencesKt.count(_Sequences.kt:1235)
        at com.official.standard.SequencesKt.main(Sequences.kt:28)
     */
    // println(oddNumbers.count()) // error: the sequence is infinite

    val oddNumbersLessThan10 = generateSequence(1) { if (it < 8) it + 2 else null }
    println(oddNumbersLessThan10.count())


    /*
        最后，还有一个函数可以让您一个接一个地或按任意大小的块生成序列元素——sequence() 函数。 这个函数接受
        一个包含调用 yield() 和 yieldAll() 函数的 lambda 表达式。 它们将一个元素返回给序列消费者并暂停
        sequence() 的执行，直到消费者请求下一个元素。 yield() 将单个元素作为参数； yieldAll() 可以接受一
        个 Iterable 对象、一个 Iterator 或另一个 Sequence。 yieldAll() 的 Sequence 参数可以是无限的。
        但是，这样的调用必须是最后一次：所有后续调用将永远不会被执行。
     */
    val oddNumbers2 = sequence {
        yield(1)
        yield(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }

    println(oddNumbers2.take(5).toList())

    // https://kotlinlang.org/docs/sequences.html#sequence-processing-example
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)

    //convert the List to a Sequence
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // terminal operation: obtaining the result as a List
    println(lengthsSequence.toList())

}