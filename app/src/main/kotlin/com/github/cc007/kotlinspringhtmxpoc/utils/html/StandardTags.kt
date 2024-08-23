package com.github.cc007.kotlinspringhtmxpoc.utils.html

import com.github.cc007.kotlinspringhtmxpoc.utils.html.HtmlTag.Builder

val Builder.head by BuilderDelegate

object head {
    val Builder.title by BuilderDelegate

    val Builder.script
        get() = object : InvokableWithSrcNoConf {
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

    val Builder.a
        get() = InvokableWithHref { href, configure ->
            tag("a") {
                attribute("href" to href)
                configure()
            }
        }
}