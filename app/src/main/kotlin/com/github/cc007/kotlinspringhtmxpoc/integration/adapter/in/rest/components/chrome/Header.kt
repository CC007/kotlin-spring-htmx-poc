package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.attr.classes
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
        classes("bg-zinc-100", "px-2", "py-1", "shrink-0", "flex", "gap-5", "justify-start", "items-center")
        div {
            classes("w-10 h-10")
            hxGet(href = "/", target = REF_CONTENT) {
                attribute("preload" to "mouseover")
                img {
                    classes("w-auto", "h-full", "rounded")
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