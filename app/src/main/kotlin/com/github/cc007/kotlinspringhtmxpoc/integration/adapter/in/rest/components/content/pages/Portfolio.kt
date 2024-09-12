package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages

import com.github.cc007.dsl.html.HtmlTag.Builder
import com.github.cc007.dsl.html.tag.body.li
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.dsl.html.tag.body.ul
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.REF_CONTENT

fun Builder.Portfolio() {
    p { text("This is the portfolio section.") }
    ul {
        li {
            hxGet("/bigben", REF_CONTENT) {
                attribute("preload" to "mouseover")
                attribute("preload-images" to "true")
                text("Big Ben")
            }
        }
    }
}
