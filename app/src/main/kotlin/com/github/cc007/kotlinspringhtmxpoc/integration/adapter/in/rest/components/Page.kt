package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.dsl.html.attr.id
import com.github.cc007.dsl.html.tag.body
import com.github.cc007.dsl.html.tag.body.section
import com.github.cc007.dsl.html.tag.head
import com.github.cc007.dsl.html.tag.head.meta
import com.github.cc007.dsl.html.tag.head.style
import com.github.cc007.dsl.html.tag.head.title
import com.github.cc007.dsl.html.tag.script
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome.Footer
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome.Header
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import jakarta.servlet.http.HttpServletRequest

context(HttpServletRequest)
fun HtmlTag.Builder.Page(content: HtmlTag.Builder.() -> Unit) {
    attribute("lang" to "en")
    head {
        meta { attribute("charset" to "UTF-8") }
        title("CC007 - ${model["title"]}")
        script("https://unpkg.com/htmx.org@1.9.12/dist/htmx.min.js")
        script("https://unpkg.com/htmx.org@1.9.12/dist/ext/response-targets.js")
        script("https://unpkg.com/htmx.org@1.9.12/dist/ext/preload.js")
        script("https://cdn.tailwindcss.com")
        style("/css/styles.css")
    }
    body {
        classes("h-dvh", "flex", "flex-col")
        attribute("hx-ext" to "response-targets, preload")
        Header()
        section {
            id("content")
            classes("p-5", "grow", "overflow-y-auto")
            content()
        }
        Footer()
    }
}