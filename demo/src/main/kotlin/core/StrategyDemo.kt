package core

data class ApplyEvent(val money: Int, val title: String)

interface ApplyHandler {
    val successor: ApplyHandler?
    fun handleEvent(event: ApplyEvent)
}

class GroupLeader(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 100 -> println("Group leader handled application: ${event.title}.")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("Group Leader: This application cannot be handled.")
            }
        }
    }
}

class President(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money <= 500 -> println("President handled application:  ${event.title}.")
            else -> when (successor) {
                is ApplyHandler -> successor.handleEvent(event)
                else -> println("President: This application cannot be handled.")
            }
        }
    }
}

class College(override val successor: ApplyHandler?) : ApplyHandler {
    override fun handleEvent(event: ApplyEvent) {
        when {
            event.money > 1000 -> println("College: This application is refused.")
            else -> println("College handled application: ${event.title}.")
        }
    }
}

class PartialFunction<in P1, out R>(private val defineAt: (P1) -> Boolean,
                                    private val f: (P1) -> R) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (defineAt(p1)) {
            return f(p1)
        } else {
            throw IllegalArgumentException("Value: ($p1) isn't supported by this function")
        }
    }

    fun isDefinedAt(p1: P1) = defineAt(p1)
}

infix fun <P1, R> PartialFunction<P1, R>.orElse(that: PartialFunction<P1, R>):
        PartialFunction<P1, R> {
    return PartialFunction({ this.isDefinedAt(it) || that.isDefinedAt(it) }) {
        when {
            this.isDefinedAt(it) -> this(it)
            else -> that(it)
        }
    }
}

fun main() {
    val college = College(null)
    val president = President(college)
    val groupLeader = GroupLeader(president)

    groupLeader.handleEvent(ApplyEvent(10, "buy a pen"))
    groupLeader.handleEvent(ApplyEvent(200, "team building"))
    groupLeader.handleEvent(ApplyEvent(600, "hold a debate match"))
    groupLeader.handleEvent(ApplyEvent(1200, "annual meeting of the college"))


    val groupLeader2 = {
        val defineAt: (ApplyEvent) -> Boolean = { it.money <= 200 }
        val handler: (ApplyEvent) -> Unit = { println("Group Leader handled application:${it.title}.") }
        PartialFunction(defineAt, handler)
    }()

    val president2 = {
        val defineAt: (ApplyEvent) -> Boolean = { it.money <= 500 }
        val handler: (ApplyEvent) -> Unit = { println("President handled application:${it.title}.") }
        PartialFunction(defineAt, handler)
    }()

    val college2 = {
        val defineAt: (ApplyEvent) -> Boolean = { true }
        val handler: (ApplyEvent) -> Unit = {
            when {
                it.money > 1000 -> println("College: This application is refused.")
                else -> println("College handled application: ${it.title}.")
            }
        }
        PartialFunction(defineAt, handler)
    }()

    val applyChain = groupLeader2 orElse president2 orElse college2

    applyChain(ApplyEvent(600, "hold a debate match"))
}