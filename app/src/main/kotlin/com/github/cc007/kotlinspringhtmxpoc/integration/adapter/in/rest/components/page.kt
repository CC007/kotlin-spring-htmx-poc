package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.body.a
import com.github.cc007.dsl.html.body.br
import com.github.cc007.dsl.html.body.h1
import com.github.cc007.dsl.html.body.p
import com.github.cc007.dsl.html.classes

object page {


    fun HtmlTag.Builder.App() {
        h1 {
            classes("text-3xl", "font-bold", "underline")
            text("Hello World!")
        }
        p {
            text("Lorum ipsum")
            br {}
            a("http://duckduckgo.com") {
                text("Go to search")
            }
        }
    }
}