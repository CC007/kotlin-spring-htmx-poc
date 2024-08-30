package com.github.cc007.dsl.html

import com.github.cc007.dsl.html.HtmlTag.Builder


interface InvokableWithSrcNoConf {
    operator fun invoke(
        src: String,
        configure: Builder.() -> Unit = {},
    )
}

interface InvokableWithTextNoConf {
    operator fun invoke(
        text: String,
        configure: Builder.() -> Unit = {},
    )
}

fun interface InvokableWithHref {
    operator fun invoke(
        href: String,
        configure: Builder.() -> Unit,
    )
}