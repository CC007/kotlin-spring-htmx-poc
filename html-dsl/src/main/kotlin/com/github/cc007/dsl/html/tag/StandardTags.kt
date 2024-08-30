package com.github.cc007.dsl.html.tag

import com.github.cc007.dsl.html.*
import com.github.cc007.dsl.html.HtmlTag.Builder

object noroot {
    val NoRoot.Builder.div by BuilderDelegate
    val NoRoot.Builder.span by BuilderDelegate
    val NoRoot.Builder.h1 by BuilderDelegate
    val NoRoot.Builder.h2 by BuilderDelegate
    val NoRoot.Builder.h3 by BuilderDelegate
    val NoRoot.Builder.h4 by BuilderDelegate
    val NoRoot.Builder.h5 by BuilderDelegate
    val NoRoot.Builder.h6 by BuilderDelegate
    val NoRoot.Builder.p by BuilderDelegate

    val NoRoot.Builder.br by SelfClosingBuilderDelegate

    val NoRoot.Builder.a get() = aWithHref()
    val NoRoot.Builder.title get() = titleWithText()
    val NoRoot.Builder.script get() = scriptWithSrc()
}

val Builder.script get() = scriptWithSrc()

val Builder.head by BuilderDelegate
object head {
    val Builder.title get() = titleWithText()
}

val Builder.body by BuilderDelegate
object body {
    val Builder.div by BuilderDelegate
    val Builder.span by BuilderDelegate
    val Builder.h1 by BuilderDelegate
    val Builder.h2 by BuilderDelegate
    val Builder.h3 by BuilderDelegate
    val Builder.h4 by BuilderDelegate
    val Builder.h5 by BuilderDelegate
    val Builder.h6 by BuilderDelegate
    val Builder.p by BuilderDelegate

    val Builder.br by SelfClosingBuilderDelegate

    val Builder.a get() = aWithHref()

}

private fun BuilderWithTags.aWithHref() =
    InvokableWithHref { href, configure ->
        tag("a") {
            attribute("href" to href)
            configure()
        }
    }

private fun BuilderWithTags.titleWithText() =
    object : InvokableWithTextNoConf {
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
    object : InvokableWithSrcNoConf {
        override fun invoke(
            src: String,
            configure: Builder.() -> Unit,
        ) {
            tag("script") {
                attribute("src" to src)
                configure()
            }
        }
    }