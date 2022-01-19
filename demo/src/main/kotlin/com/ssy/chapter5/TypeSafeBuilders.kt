@file:Official

package com.ssy.chapter5

import com.ssy.Official

/**
 * todo 深入研究下DSLs
 * DSLs (domain-specific languages)
 */

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

/*
    如果注解类使用@DslMarker 注解进行注解，则称为 DSL注解。

    控制接收者作用域的特殊机制
 */
@DslMarker
annotation class HtmlTagMarker

/*
    In our DSL all the tag classes extend the same superclass Tag. It's enough to annotate only the
    superclass with @HtmlTagMarker and after that the Kotlin compiler will treat all the inherited
    classes as annotated:
 */
@HtmlTagMarker
abstract class Tag(val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, indent + "  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String {
        val builder = StringBuilder()
        for ((attr, value) in attributes) {
            builder.append(" $attr=\"$value\"")
        }
        return builder.toString()
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

abstract class TagWithText(name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        children.add(TextElement(this))
    }
}

class HTML : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)

    fun body() {

    }

    fun body2(): Int {
        return 0
    }
}

class Head : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: BodyB.() -> Unit) = initTag(BodyB(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: BodyA.() -> Unit) {
        val a = initTag(BodyA(), init)
        a.href = href
    }
}

class Body : BodyTag("body")
class BodyB : BodyTag("b")
class P : BodyTag("p")
class H1 : BodyTag("h1")
class LI() : BodyTag("li")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class BodyA : BodyTag("a") {
    var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}
//
//fun <K, V> buildMap(builder: MutableMap<K, V>.() -> Unit): Map<K, V> {
//
//}
//
//fun addEntryToMap(baseMap: Map<String, Number>, additionalEntry: Pair<String, Int>?) {
//    val myMap = buildMap {
//        putAll(baseMap)
//        if (additionalEntry != null) {
//            put(additionalEntry.first, additionalEntry.second)
//        }
//    }
//}


fun main() {
    val result = html {                                            // 1
        head {                                                     // 2
            title { +"HTML encoding with Kotlin" }
        }
        body {                                                     // 2
            h1 { +"HTML encoding with Kotlin" }
            p {
                +"this format can be used as an"                   // 3
                +"alternative markup to HTML"                      // 3
            }

            // an element with attributes and text content
            a(href = "http://kotlinlang.org") { +"Kotlin" }

            // mixed content
            p {
                +"This is some"
                b { +"mixed" }
                +"text. For more see the"
                a(href = "http://kotlinlang.org") {
                    +"Kotlin"
                }
                +"project"
            }
            p {
                +"some text"
                ul {
                    for (i in 1..5)
                        li { +"${i}*2 = ${i * 2}" }
                }
            }
        }
    }

    html {
        head {
            this@html.head {}
//            head {} // not compile, 不让深度嵌套， 使用DslMarker注解之后。
        }
    }
}
