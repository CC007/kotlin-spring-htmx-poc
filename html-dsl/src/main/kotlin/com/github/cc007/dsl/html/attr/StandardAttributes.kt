package com.github.cc007.dsl.html.attr

import com.github.cc007.dsl.html.HtmlTag

fun HtmlTag.Builder.classes(
    vararg classNames: String
) = appendAttribute("class" to classNames.joinToString(" "))

fun HtmlTag.Builder.id(id: String) = attribute("id" to id)
fun HtmlTag.Builder.src(src: String) = attribute("src" to src)
fun HtmlTag.Builder.alt(alt: String) = attribute("alt" to alt)
fun HtmlTag.Builder.href(href: String) = attribute("href" to href)