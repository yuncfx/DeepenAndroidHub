package base

import kotlin.reflect.KProperty

interface IBase {
    fun print()
}

class BaseImpl(val x: Int) : IBase {
    override fun print() {
        print(x)
    }
}

class DerivedFromBase(b: IBase) : IBase by b

fun main(args: Array<String>) {
    val b = BaseImpl(10)
    DerivedFromBase(b).print()

    println()

    val e = Example()
    println(e.p)
    e.p = "Runoob"
    println(e.p)

    val p = Person("sanshi")
    p.emails
    println("----")
    p.emails

    val pLazy = PersonLazy("sanshiLazy")
    pLazy.emailsLazy
    println("...........")
    pLazy.emailsLazy
}

class Example {
    var p: String by Delegate()
}

class Delegate {
    operator fun getValue(thisRef: Example, property: KProperty<*>): String {
        return "$thisRef, delegated ${property.name} attribute"
    }

    operator fun setValue(thisRef: Example, property: KProperty<*>, value: String): Unit {
        println("$thisRef has an attribute ${property.name} value as $value")
    }
}

class Email {}

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf()
}

open class Person(open var name: String?) {
    private var _emails: List<Email>? = null

    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmails(this)
            }
            return _emails!!
        }

    fun method() : String {
        return "helloPerson"
    }

    override fun toString():String {
        return name?:"null"
    }
}


class PersonLazy(override var name: String?) : Person(name) {
    val emailsLazy by lazy {
        loadEmails(this)
    }
}

