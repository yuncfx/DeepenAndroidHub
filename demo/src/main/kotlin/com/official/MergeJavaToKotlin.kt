@file:Official("https://kotlinlang.org/docs/java-to-kotlin-collections-guide.html")

package com.official


import com.ssy.Official
import java.util.stream.IntStream
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

class Version(val major: Int, val minor: Int) : Comparable<Version> {
    override fun compareTo(other: Version): Int {
        if (this.major != other.major) {
            return this.major - other.major
        }

        return this.minor - other.minor
    }
}

data class Person(val name: String, val age: Int)

data class Request(
    val url: String,
    val responseCode: Int
)

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

    val input3 =
        "What is the answer to the Ultimate Question of Life, the Universe, and Everything? 42"
    val answer = input3.substringAfter("?")
    println(answer)

    val input4 = "To be, or not to be, that is the question."
    val question = input4.substringAfterLast(",")
    println(question)

    println("---range test---")
    val versionRange = Version(1, 11)..Version(1, 30)
    println(Version(0, 9) in versionRange)
    println(Version(1, 20) in versionRange)

    println("---sort test---")
    val persons = listOf(
        Person("Jack", 35), Person("David", 30),
        Person("Jack", 25)
    )
    // todo 为什么能传入这个参数？
    persons.sortedWith(compareBy(Person::name, Person::age))


    println("sequence test---")
    val sumJava = IntStream.iterate(1) {
        print((it + 3).toString() + ", ")
        it + 3
    }.limit(10).sum()
    println(sumJava)

    val sumKotlin = generateSequence(1) { it + 3 }.take(10).sum()
    println(sumKotlin)

    println("remove test---")
    val numbers = mutableListOf(1, 2, 3, 1)
    numbers.removeAt(0)
    println(numbers) // [2, 3, 1]
    numbers.remove(1)
    println(numbers) // [2, 3]

    val map = mutableMapOf("zhangsan" to 12, "lisi" to 20)
    for ((k, v) in map) {
        println("Key = $k, Value = $v")
    }

    map.forEach { (k, v) -> println("Key = $k, Value = $v") }

    println("last element test---")
    val emails = listOf<String>()
    val theOldestEmail = emails.lastOrNull() ?: ""
    val theFreshestEmail = emails.firstOrNull() ?: ""

    println("copy set test---")
    val sourceList = listOf(1, 2, 3, 1)
    println(sourceList.toSet())

    println("group by test---")
    val requests = listOf(
        Request("https://kotlinlang.org/docs/home.html", 200),
        Request("https://kotlinlang.org/docs/home.html", 400),
        Request("https://kotlinlang.org/docs/comparison-to-java.html", 200)
    )
    println(requests.groupBy(Request::url))

    println("filter elements---")
    val numbers2 = mapOf("key1" to 1, "key2" to 2, "key3" to 3, "key11" to 11)
    val filteredNumbers = numbers2.filter { (key, value) -> key.endsWith("1") && value > 10 }
    println(filteredNumbers)

    println("filter smart cast---")
    val numbers3 = listOf(null, 1, "two", 3.0, "four")
    println("All String elements in upper case:")
    numbers3.filterIsInstance<String>().forEach {
        println(it.uppercase())
    }

    val numbers4 = listOf("one", "two", "three", "four")
    println(numbers4.none { it.endsWith("e") })
    println(numbers4.any { it.endsWith("e") })
    println(numbers4.all { it.endsWith("e") })

    println("zip test---")
    // zip() returns the list of Pair objects
    val colors = listOf("red", "brown")
    val animals = listOf("fox", "bear", "wolf")
    println(colors.zip(animals) { color, animal ->
        "The ${animal.replaceFirstChar { it.uppercase() }} is $color}"
    })

    println("associate test---")
    val numbers5 = listOf("one", "two", "three", "four")
    println(numbers5.associateWith { it.length })
}