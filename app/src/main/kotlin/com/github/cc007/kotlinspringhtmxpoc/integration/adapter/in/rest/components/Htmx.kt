package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.NoRoot
import com.github.cc007.dsl.html.attr.id
import com.github.cc007.dsl.html.tag.body.div
import com.github.cc007.dsl.html.tag.head.title
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome.Menu

//<title>CC007 - ${title}</title>
//<div id="menu" hx-swap-oob="true">
//    <#include "header/menu.ftl">
//</div>
//<#include contentTemplate + ".ftl">
fun NoRoot.Builder.Htmx(title: String, content: BuilderWithTags.() -> Unit) {
    title("CC007 - $title")
    div {
        id("menu")
        attribute("hx-swap-oob" to "true")
        Menu()
    }
    content()
}