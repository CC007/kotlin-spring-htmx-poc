package com.github.cc007.kotlinspringhtmxpoc.utils.html

fun HtmlTag.Builder.classes(
    vararg classNames: String
) = attribute("class" to classNames.joinToString(" "))
