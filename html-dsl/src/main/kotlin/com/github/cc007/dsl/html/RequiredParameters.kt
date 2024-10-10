package com.github.cc007.dsl.html

import com.github.cc007.dsl.html.HtmlTag.Builder


interface InvokableWithSrcOptConf {
    operator fun invoke(
        src: String,
        configure: Builder.() -> Unit = {},
    )
}

interface InvokableWithTextOptConf {
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
fun interface InvokableWithType<T> {
    operator fun invoke(
        type: T?,
        configure: Builder.() -> Unit,
    )
}

fun interface InvokableWithHrefNoConf {
    operator fun invoke(
        href: String
    )
}