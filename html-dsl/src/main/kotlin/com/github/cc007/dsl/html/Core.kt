package com.github.cc007.dsl.html

import com.github.cc007.dsl.html.HtmlTag.Builder
import uy.klutter.core.collections.asReadOnly
import kotlin.reflect.KProperty

var indentLength: Int = 4

fun interface HtmlElement {
    fun render(indent: String): String
    val stringify
        get() = render("")
}

class HtmlTag private constructor(
    val name: String,
    val attributes: Map<String, String>,
    val children: List<HtmlElement>,
    val selfClosing: Boolean
) : HtmlElement {

    override fun render(indent: String): String {
        val attributeString = attributes.entries.joinToString(separator = " ") { (key, value) ->
            "$key=\"$value\""
        }.run {
            if (isEmpty()) "" else " $this"
        }
        if (selfClosing) {
            return "$indent<$name$attributeString />"
        }
        val childrenString = children.joinToString("\n") {
            it.render(indent + " ".repeat(indentLength))
        }
        if (childrenString.isEmpty()) {
            return "$indent<$name$attributeString></$name>"
        }
        return """
            |$indent<$name$attributeString>
            |$childrenString
            |$indent</$name>
        """.trimMargin()
    }

    class Builder(
        private val name: String,
        private val attributes: MutableMap<String, String> = mutableMapOf(),
        private val childElements: MutableList<HtmlElement> = mutableListOf(),
        var selfClosing: Boolean = false
    ) {
        fun attribute(attribute: Pair<String, String>) {
            attributes += attribute
        }

        fun element(child: HtmlElement) {
            childElements += child
        }

        fun replaceLastElement(child: HtmlElement) {
            childElements.removeLast()
            childElements += child
        }

        fun text(text: String? = null, textProvider: () -> String = { "" }) {
            val usedText = text ?: textProvider()
            element { indent -> "$indent$usedText" }
        }

        fun build() =
            HtmlTag(name, attributes.asReadOnly(), childElements.asReadOnly(), selfClosing)

    }
}

interface Invokable {
    operator fun invoke(configure: Builder.() -> Unit = {})
}

object BuilderDelegate {
    operator fun getValue(thisRef: Builder, property: KProperty<*>): Invokable {
        return object : Invokable {
            override fun invoke(configure: Builder.() -> Unit) {
                thisRef.tag(property.name, configure)
            }
        }
    }
}

object SelfClosingBuilderDelegate {
    operator fun getValue(thisRef: Builder, property: KProperty<*>): Invokable {
        thisRef.tag(property.name) { selfClosing = true }
        return object : Invokable {
            override fun invoke(configure: Builder.() -> Unit) {
                thisRef.replaceLastTag(property.name) {
                    selfClosing = true
                    configure()
                }
            }
        }
    }
}

fun htmlTag(configure: Builder.() -> Unit): HtmlElement {
    val builder = Builder("html")
    builder.configure()
    return builder.build()
}

fun html(configure: Builder.() -> Unit): String {
    val builder = Builder("html")
    builder.configure()
    return builder.build().stringify
}

fun Builder.tag(
    name: String,
    configure: Builder.() -> Unit = { }
) = element(Builder(name).apply(configure).build())

fun Builder.replaceLastTag(
    name: String,
    configure: Builder.() -> Unit = { }
) = replaceLastElement(Builder(name).apply(configure).build())