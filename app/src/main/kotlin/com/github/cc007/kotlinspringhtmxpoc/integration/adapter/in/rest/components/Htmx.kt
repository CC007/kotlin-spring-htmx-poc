package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.NoRoot
import com.github.cc007.dsl.html.attr.id
import com.github.cc007.dsl.html.tag.body.div
import com.github.cc007.dsl.html.tag.head.title
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome.Menu
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import jakarta.servlet.http.HttpServletRequest

context(HttpServletRequest)
fun NoRoot.Builder.Htmx(content: BuilderWithTags.() -> Unit) {
    title("CC007 - ${model["title"]}")
    div {
        id("menu")
        attribute("hx-swap-oob" to "true")
        Menu()
    }
    content()
}