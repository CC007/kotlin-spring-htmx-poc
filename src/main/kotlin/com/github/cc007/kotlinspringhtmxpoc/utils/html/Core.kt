package com.github.cc007.kotlinspringhtmxpoc.utils.html

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

        fun build() =
            HtmlTag(name, attributes.asReadOnly(), childElements.asReadOnly(), selfClosing)

    }
}

fun html(configure: HtmlTag.Builder.() -> Unit): HtmlElement {
    val builder = HtmlTag.Builder("html")
    builder.configure()
    return builder.build()
}

fun HtmlTag.Builder.text(text: String) = element { indent -> "$indent$text" }

fun HtmlTag.Builder.tag(
    name: String,
    configure: HtmlTag.Builder.() -> Unit = { }
) = element(HtmlTag.Builder(name).apply(configure).build())