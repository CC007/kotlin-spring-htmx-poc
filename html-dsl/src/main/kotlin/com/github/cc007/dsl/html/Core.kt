package com.github.cc007.dsl.html

import com.github.cc007.dsl.html.HtmlTag.Builder
import com.github.cc007.dsl.html.tag.body
import com.github.cc007.dsl.html.tag.head
import uy.klutter.core.collections.asReadOnly

var indentLength: Int = 4

fun interface HtmlElement {
    fun render(indent: String): String
    val stringify
        get() = render("")
}

interface BuilderWithTags {
    fun tag(name: String, configure: Builder.() -> Unit = { }): HtmlElement
    fun replaceTag(oldTag: HtmlElement, name: String, configure: Builder.() -> Unit = { })
}

class HtmlTag private constructor(
    private val name: String,
    private val attributes: Map<String, String?>,
    private val children: List<HtmlElement>,
    private val selfClosing: Boolean
) : HtmlElement {

    override fun render(indent: String): String {
        val attributeString = attributes.entries.joinToString(separator = " ") { (key, value) ->
            if (value == null) key else "$key=\"$value\""
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
            ||$indent<$name$attributeString>
            ||$childrenString
            ||$indent</$name>
        """.trimMargin("||")
    }

    class Builder(
        private val name: String,
        private val attributes: MutableMap<String, String?> = mutableMapOf(),
        private val children: MutableList<HtmlElement> = mutableListOf(),
        var selfClosing: Boolean = false
    ): BuilderWithTags {
        fun attribute(attribute: Pair<String, String?>) {
            attributes += attribute
        }

        fun child(child: HtmlElement) {
            children += child
        }

        fun replaceChild(oldChild: HtmlElement, child: HtmlElement) {
            children -= oldChild
            children += child
        }

        fun text(text: String? = null, textProvider: () -> String = { "" }) {
            val usedText = text ?: textProvider()
            child { indent -> "$indent$usedText" }
        }

        override fun tag(name: String, configure: Builder.() -> Unit): HtmlElement {
            val child = Builder(name).apply(configure).build()
            child(child)
            return child
        }

        override fun replaceTag(
            oldTag: HtmlElement,
            name: String,
            configure: Builder.() -> Unit
        ) = replaceChild(oldTag, Builder(name).apply(configure).build())

        fun build() = HtmlTag(name, attributes.asReadOnly(), children.asReadOnly(), selfClosing)
    }
}

class NoRoot private constructor(
    private val children: List<HtmlElement>,
    private val includeDoctype: Boolean
): HtmlElement {

    override fun render(indent: String): String {
        val htmlString = children.joinToString("\n") {
            it.render("")
        }
        return if (includeDoctype) "<!DOCTYPE html>\n$htmlString" else htmlString
    }

    class Builder(
        private val childElements: MutableList<HtmlElement> = mutableListOf(),
        var includeDoctype: Boolean = false
    ): BuilderWithTags {
        override fun tag(name: String, configure: HtmlTag.Builder.() -> Unit): HtmlElement {
            val tag = Builder(name).apply(configure).build()
            childElements += tag
            return tag
        }

        override fun replaceTag(oldTag: HtmlElement, name: String, configure: HtmlTag.Builder.() -> Unit) {
            val tag = Builder(name).apply(configure).build()
            childElements -= oldTag
            childElements += tag
        }

        fun build() = NoRoot(childElements.asReadOnly(), includeDoctype)
    }
}

fun tag(name: String, configure: Builder.() -> Unit = {}): HtmlElement {
    val builder = Builder(name)
    builder.configure()
    return builder.build()
}

fun noRootTag(configure: NoRoot.Builder.() -> Unit = {}): HtmlElement {
    val builder = NoRoot.Builder()
    builder.configure()
    return builder.build()
}

fun noRoot(configure: NoRoot.Builder.() -> Unit = {}): String {
    return noRootTag(configure).stringify
}

fun htmlTag(configure: Builder.() -> Unit): HtmlElement {
    return noRootTag {
        includeDoctype = true
        tag("html", configure)
    }
}

fun html(configure: Builder.() -> Unit): String {
    return htmlTag(configure).stringify
}

fun htmlTag(head: Builder.() -> Unit, body: Builder.() -> Unit): HtmlElement {
    return tag("html") {
        head(head)
        body(body)
    }
}

fun html(head: Builder.() -> Unit, body: Builder.() -> Unit): String {
    return htmlTag(head, body).stringify
}