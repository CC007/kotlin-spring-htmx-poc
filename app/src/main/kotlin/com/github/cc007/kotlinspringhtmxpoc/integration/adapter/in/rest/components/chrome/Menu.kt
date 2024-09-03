package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet

//<#list menuItems as menuItem>
//  <#assign activeParam=(menuItem.active?? && menuItem.active)?then(' class="active"','') >
//  <@a href="${menuItem.url}" otherParams=(activeParam + ' preload="mouseover"')>${menuItem.name}</@a>
//</#list>
fun HtmlTag.Builder.Menu() {
    for (menuItem in getMenuItems()) {
        hxGet(menuItem.url, "#content") {
            if (menuItem.active) classes("active")
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

fun getMenuItems(): List<MenuItem> {
    return listOf(MenuItem("Home", "/", true))
}
