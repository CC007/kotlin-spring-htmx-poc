package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.chrome

import com.github.cc007.dsl.html.HtmlTag
import com.github.cc007.dsl.html.tag.body.footer
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.REF_CONTENT


//<div class="bg-zinc-50 px-2 py-1 shrink-0">
//  <@a href="/tos" otherParams=' preload="mouseover"'>Terms of Service</@a>
//  |
//  <@a href="/contact" otherParams=' preload="mouseover"'>Contact</@a>
//  |
//  <@a href="/trigger-error" otherParams=' preload="mouseover"'>Trigger error</@a>
//</div>
fun HtmlTag.Builder.Footer() {
    footer {
        //classes("bg-zinc-50", "px-2", "py-1", "shrink-0")
        hxGet(href = "/tos", target = REF_CONTENT) {
            attribute("preload" to "mouseover")
            text("Terms of Service")
        }
        text("|")
        hxGet(href = "/contact", target = REF_CONTENT) {
            attribute("preload" to "mouseover")
            text("Contact")
        }
        text("|")
        hxGet(href = "/trigger-error", target = REF_CONTENT) {
            attribute("preload" to "mouseover")
            text("Trigger error")
        }
    }
}