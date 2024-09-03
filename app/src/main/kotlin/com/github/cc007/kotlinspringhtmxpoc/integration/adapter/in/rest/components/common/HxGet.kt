package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common

import com.github.cc007.dsl.html.HtmlTag.Builder

//<a href="${href}" hx-get="${href}" hx-target="${target}" hx-target-*="${target}" hx-push-url="true"${otherParams}><#nested></a>
val Builder.hxGet
    get() = InvokableWithHrefAndTarget { href, target, configure ->
        tag("a") {
            attribute("href" to href)
            attribute("hx-get" to href)
            attribute("hx-target" to target)
            attribute("hx-target-*" to target)
            attribute("hx-push-url" to "true")
            configure()
        }
    }

fun interface InvokableWithHrefAndTarget {
    operator fun invoke(
        href: String,
        target: String,
        configure: Builder.() -> Unit
    )
}