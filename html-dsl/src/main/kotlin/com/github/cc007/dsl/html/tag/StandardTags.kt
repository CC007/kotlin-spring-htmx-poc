package com.github.cc007.dsl.html.tag

import com.github.cc007.dsl.html.*
import com.github.cc007.dsl.html.HtmlTag.Builder
import com.github.cc007.dsl.html.attr.href
import com.github.cc007.dsl.html.attr.src

val BuilderWithTags.script get() = scriptWithSrc()

val BuilderWithTags.head by BuilderDelegate
object head {
    val BuilderWithTags.meta by SelfClosingBuilderDelegate
    val BuilderWithTags.link by SelfClosingBuilderDelegate
    val BuilderWithTags.title get() = titleWithText()
    val BuilderWithTags.style get() = cssLinkWithHref()
}

val BuilderWithTags.body by BuilderDelegate
object body {
    val BuilderWithTags.div by BuilderDelegate
    val BuilderWithTags.span by BuilderDelegate
    val BuilderWithTags.h1 by BuilderDelegate
    val BuilderWithTags.h2 by BuilderDelegate
    val BuilderWithTags.h3 by BuilderDelegate
    val BuilderWithTags.h4 by BuilderDelegate
    val BuilderWithTags.h5 by BuilderDelegate
    val BuilderWithTags.h6 by BuilderDelegate
    val BuilderWithTags.p by BuilderDelegate
    val BuilderWithTags.header by BuilderDelegate
    val BuilderWithTags.section by BuilderDelegate
    val BuilderWithTags.footer by BuilderDelegate
    val BuilderWithTags.ul by BuilderDelegate
    val BuilderWithTags.li by BuilderDelegate

    val BuilderWithTags.br by SelfClosingBuilderDelegate
    val BuilderWithTags.img by SelfClosingBuilderDelegate

    val BuilderWithTags.a get() = aWithHref()

}

private fun BuilderWithTags.aWithHref() =
    InvokableWithHref { href, configure ->
        tag("a") {
            href(href)
            configure()
        }
    }

private fun BuilderWithTags.cssLinkWithHref() =
    InvokableWithHrefNoConf { href ->
        tag("link") {
            attribute("rel" to "stylesheet")
            href(href)
        }
    }

private fun BuilderWithTags.titleWithText() =
    object : InvokableWithTextOptConf {
        override fun invoke(
            text: String,
            configure: Builder.() -> Unit,
        ) {
            tag("title") {
                text(text)
                configure()
            }
        }
    }

private fun BuilderWithTags.scriptWithSrc() =
    object : InvokableWithSrcOptConf {
        override fun invoke(
            src: String,
            configure: Builder.() -> Unit,
        ) {
            tag("script") {
                src(src)
                configure()
            }
        }
    }