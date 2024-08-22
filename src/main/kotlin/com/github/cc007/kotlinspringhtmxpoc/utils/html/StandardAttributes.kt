package com.github.cc007.kotlinspringhtmxpoc.utils.html

import com.github.cc007.kotlinspringhtmxpoc.utils.html.HtmlTag.Builder

fun HtmlTag.Builder.classes(
    vararg classNames: String
) = attribute("class" to classNames.joinToString(" "))


interface InvokableWithSrcNoConf {
    operator fun invoke(
        src: String,
        configure: Builder.() -> Unit = {},
    )
}

fun interface InvokableWithHref {
    operator fun invoke(
        href: String,
        configure: Builder.() -> Unit,
    )
}