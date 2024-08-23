package com.github.cc007.dsl.html

import com.github.cc007.dsl.html.HtmlTag.Builder
import uy.klutter.core.collections.asReadOnly

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

        fun replaceElement(oldChild: HtmlElement, child: HtmlElement) {
            childElements -= oldChild
            childElements += child
        }

        fun text(text: String? = null, textProvider: () -> String = { "" }) {
            val usedText = text ?: textProvider()
            element { indent -> "$indent$usedText" }
        }

        fun tag(name: String, configure: Builder.() -> Unit = { }): HtmlElement {
            val child = Builder(name).apply(configure).build()
            element(child)
            return child
        }

        fun replaceTag(
            oldTag: HtmlElement,
            name: String,
            configure: Builder.() -> Unit = { }
        ) = replaceElement(oldTag, Builder(name).apply(configure).build())

        fun build() =
            HtmlTag(name, attributes.asReadOnly(), childElements.asReadOnly(), selfClosing)

    }
}

fun tag(name: String, configure: Builder.() -> Unit = {}): HtmlElement {
    val builder = Builder(name)
    builder.configure()
    return builder.build()
}

fun htmlTag(configure: Builder.() -> Unit): HtmlElement {
    return tag("html", configure)
}

fun html(configure: Builder.() -> Unit): String {
    return htmlTag(configure).stringify
}

