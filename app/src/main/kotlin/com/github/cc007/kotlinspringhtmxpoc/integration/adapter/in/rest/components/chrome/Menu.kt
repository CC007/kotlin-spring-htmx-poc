package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome

import com.github.cc007.dsl.html.HtmlTag.Builder
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.ContentPageType
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.REF_CONTENT
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import jakarta.servlet.http.HttpServletRequest

context(HttpServletRequest)
fun Builder.Menu() {
    classes("bg-zinc-200", "rounded", "p-1", "flex", "items-center")
    for (menuItem in getMenuItems()) {
        hxGet(menuItem.url, REF_CONTENT) {
            classes("px-2", "py-1", "grow", "text-zinc-950", "text-center")
            if (menuItem.active) classes("bg-zinc-300", "rounded")
            attribute("preload" to "mouseover")
            text(menuItem.name)
        }
    }
}

data class MenuItem (
    val name: String,
    val url: String,
    val active: Boolean,
)

context(HttpServletRequest)
fun getMenuItems(): List<MenuItem> {
    return ContentPageType.entries.map {
        MenuItem(it.title,"/${it.page}", it == model["contentPage"])
    }
}
