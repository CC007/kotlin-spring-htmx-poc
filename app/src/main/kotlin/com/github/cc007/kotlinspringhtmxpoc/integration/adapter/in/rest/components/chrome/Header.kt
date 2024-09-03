package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.attr.id
import com.github.cc007.dsl.html.tag.body.div
import com.github.cc007.dsl.html.tag.body.header
import com.github.cc007.dsl.html.tag.body.img
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.REF_CONTENT

//<div class="bg-zinc-50 px-2 py-1 shrink-0 flex justify-center items-center">
//  <div id="logo"><@a href="/" otherParams=' preload="mouseover"'><img src="/images/logo.png" alt="${logo}"></@a></div>
//  <div id="menu">
//      <#include "header/menu.ftl">
//  </div>
//</div>
fun HtmlTag.Builder.Header() {
    header {
        //classes("bg-zinc-50", "px-2", "py-1", "shrink-0", "flex", "justify-center", "items-center")
        div {
            id("logo")
            hxGet(href = "/", target = REF_CONTENT) {
                attribute("preload" to "mouseover")
                img {
                    attribute("src" to "/images/logo.png")
                    attribute("alt" to "CC007's logo")
                }
            }
        }
        div {
            id("menu")
            Menu()
        }
    }
}