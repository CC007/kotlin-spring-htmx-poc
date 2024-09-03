package com.github.cc007.kotlinspringhtmxpoc.utils

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.html
import com.github.cc007.dsl.html.noRoot
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.Htmx
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.Page
import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.response(title: String, content: BuilderWithTags.() -> Unit): String {
    return if (isHtmxRequest()) {
        htmxResponse(title, content)
    } else {
        pageResponse(title, content)
    }.also { println(it) }
}

private fun HttpServletRequest.isHtmxRequest(): Boolean {
    val hxRequestHeader = getHeader("HX-Request") ?: "false"
    return hxRequestHeader == "true"
}

private fun htmxResponse(title: String, content: BuilderWithTags.() -> Unit) = noRoot {
    Htmx(title, content)
}

private fun pageResponse(title: String, content: BuilderWithTags.() -> Unit) = html {
    Page(title, content)
}