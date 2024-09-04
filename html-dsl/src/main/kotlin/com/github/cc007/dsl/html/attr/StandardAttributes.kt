package com.github.cc007.dsl.html.attr

import com.github.cc007.dsl.html.HtmlTag

fun HtmlTag.Builder.classes(
    vararg classNames: String
) = appendAttribute("class" to classNames.joinToString(" "))

fun HtmlTag.Builder.id(id: String) = attribute("id" to id)