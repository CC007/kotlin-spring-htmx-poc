package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.tag.body.a
import com.github.cc007.dsl.html.tag.body.br
import com.github.cc007.dsl.html.tag.body.h1
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.dsl.html.attr.id

object page {


    fun HtmlTag.Builder.App() {
        h1 {
            id("title")
            classes("text-3xl", "font-bold", "underline")
            text("Hello World!")
        }
        p {
            text("Lorum ipsum")
            br
            a("http://duckduckgo.com") {
                text("Go to search")
            }
        }
    }
}