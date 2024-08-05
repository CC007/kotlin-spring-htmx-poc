package com.github.cc007.kotlinspringhtmxpoc.utils.html

fun HtmlTag.Builder.classes(
    classNames: Array<String>
) = attribute("class" to classNames.joinToString(" "))