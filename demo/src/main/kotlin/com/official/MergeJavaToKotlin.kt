package com.official

import kotlin.random.Random

/*
// Java
    StringBuilder countDown = new StringBuilder();
    for (int i = 5; i > 0; i--) {
        countDown.append(i);
        countDown.append("\n");
    }
    System.out.println(countDown);
    更优雅地使用StringBuilder
 */
val countDown = buildString {
    for (i in 5 downTo 1) {
        append(i)
        appendLine()
    }
}

/*
    // Java
    List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
    String invertedOddNumbers = numbers
            .stream()
            .filter(it -> it % 2 != 0)
            .map(it -> -it)
            .map(Object::toString)
            .collect(Collectors.joining(", "));
    System.out.println(invertedOddNumbers);
 */

val numbers = listOf(1, 2, 3, 4, 5, 6)
val invertedOddNumbers = numbers.filter { it % 2 != 0 }.joinToString { "${-it}" }

fun getName(): String =
    if (Random.nextBoolean()) "" else "David"

fun main(args: Array<String>) {
    println(countDown)
    println(invertedOddNumbers) // -1, -3, -5

    // Set default value if the string is blank
    val name = getName().ifBlank { "John Doe" }
    println(name)

    val input = "##place##holder##"
    val result = input.removeSurrounding("##")
    println(result)

    val regex = Regex("""\w*\d+\w*""") // raw string
    val input2 = "login: Pokemon5, password: 1q2w3e4r5t"
    val replacementResult = regex.replace(input2, replacement = "xxx")
    println("Initial input: '$input2'")
    println("Anonymized input: '$replacementResult'")

    val input3 = "What is the answer to the Ultimate Question of Life, the Universe, and Everything? 42"
    val answer = input3.substringAfter("?")
    println(answer)

    val input4 = "To be, or not to be, that is the question."
    val question = input4.substringAfterLast(",")
    println(question)
}