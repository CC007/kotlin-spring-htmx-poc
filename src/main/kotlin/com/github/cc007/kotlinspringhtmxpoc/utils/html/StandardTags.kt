package com.github.cc007.kotlinspringhtmxpoc.utils.html

fun HtmlTag.Builder.body(
    configure: HtmlTag.Builder.() -> Unit = {}
) = tag("body", configure)

fun HtmlTag.Builder.div(
    configure: HtmlTag.Builder.() -> Unit = {}
) = tag("div", configure)

fun HtmlTag.Builder.br() = tag("br") {
    selfClosing = true
}

fun HtmlTag.Builder.p(
    configure: HtmlTag.Builder.() -> Unit = {}
) = tag("p", configure)

fun HtmlTag.Builder.a(
    href: String,
    configure: HtmlTag.Builder.() -> Unit = {}
) = tag("a") {
    attribute("href" to href)
    configure()
}