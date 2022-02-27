@file:Official("https://kotlinlang.org/docs/opt-in-requirements.html")
package com.official.standard

import com.ssy.Official
import java.util.*

/*
    todo in the future, not quite useful for me.
 */
// Library code
@RequiresOptIn(message = "This API is experimental. It may be changed in the future without notice.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class MyDateTime // Opt-in requirement annotation

@MyDateTime
class DateProvider // A class requiring opt-in

// Client code
@OptIn(MyDateTime::class)
fun getYear(): Int {
    val dateProvider: DateProvider // Error: DateProvider requires opt-in
    // ...
    return 2022
}

@MyDateTime
fun getDate(): Date {
    val dateProvider: DateProvider // OK: the function requires opt-in as well
    // ...
    return Date()
}

@OptIn(MyDateTime::class)
fun displayDate() {
    println(getDate()) // Error: getDate() requires opt-in
}



