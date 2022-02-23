package com.official.standard.collections

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 4)
    val strings = listOf("one", "two", "three", "four")
    println("count:${numbers.count()}")
    println("max:${numbers.maxOrNull()}")
    println("min:${numbers.minOrNull()}")
    println("average:${numbers.average()}")
    println("sum:${numbers.sum()}")

    println(numbers.minByOrNull { it % 2 })
    println(strings.maxWithOrNull(compareBy { it.length }))

    println(numbers.sumOf { it * 2 })

    /*
        这两个函数之间fold()和reduce()的区别在于 fold() 采用初始值并将其用作第一步的累加值，而 reduce() 的
        第一步使用第一个和第二个元素作为第一步的操作参数。
     */
    val simpleSum = numbers.reduce { sum, element -> sum + element }
    println(simpleSum)
    val simpleDoubled = numbers.fold(0) { sum, element -> sum + element * 2 }
    println(simpleDoubled)
    //incorrect: the first element isn't doubled in the result
    //val sumDoubledReduce = numbers.reduce { sum, element -> sum + element * 2 }
    //println(sumDoubledReduce)

    /*
        要以相反的顺序将函数应用于元素，请使用函数 reduceRight() 和 foldRight()。 它们以类似于 fold()
        和 reduce() 的方式工作，但从最后一个元素开始，然后继续前一个元素。 请注意，当foldRight()或
        reduceRight()时，操作参数会改变它们的顺序：首先是元素，然后是累加值。
     */
    val sumDoubledRight = numbers.foldRight(0) { element, sum -> sum + element * 2 }
    println(sumDoubledRight)

    val sumEven =
        numbers.foldIndexed(0) { idx, sum, element -> if (idx % 2 == 0) sum + element else sum }
    println(sumEven)

    val sumEvenRight =
        numbers.foldRightIndexed(0) { idx, element, sum -> if (idx % 2 == 0) sum + element else sum }
    println(sumEvenRight)

    val reduceOrNull = numbers.reduceOrNull { sum, element -> sum + element }
    println(reduceOrNull)

    println(numbers.reduceRightOrNull { element, sum -> sum + element })

    println("running test---")

    // 对于要保存中间累加器值的情况, 可以使用runningReduce和runningFold
    val runningReduceSum = numbers.runningReduce { sum, item -> sum + item }
    println(runningReduceSum)
    val runningFoldSum = numbers.runningFold(10) { sum, item -> sum + item }
    println(runningFoldSum)
    val transform = { index: Int, element: Int -> "N = ${index + 1}: $element" }
    println(
        runningReduceSum.mapIndexed(transform)
            .joinToString("\n", "Sum of first N elements with runningReduce:\n")
    )
    println(
        runningFoldSum.mapIndexed(transform)
            .joinToString("\n", "Sum of first N elements with runningFold:\n")
    )
}