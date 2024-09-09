package com.github.cc007.kotlinspringhtmxpoc.utils

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.html
import com.github.cc007.dsl.html.noRoot
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.Htmx
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.Page
import jakarta.servlet.http.HttpServletRequest

private data object RequestModel {
    val models: MutableMap<HttpServletRequest, MutableMap<String, Any?>> = mutableMapOf()
}

context(HttpServletRequest)
val model: MutableMap<String, Any?> get() = getRequestModel()

context(HttpServletRequest)
fun clearModel() = clearRequestModel()

private fun HttpServletRequest.getRequestModel(): MutableMap<String, Any?> =
    RequestModel.models.getOrPut(this) { mutableMapOf() }

private fun HttpServletRequest.clearRequestModel() {
    RequestModel.models.remove(this)
}

fun withRequest(request: HttpServletRequest, block: HttpServletRequest.() -> String): String = with(request) {
    val result = block()
    clearModel()
    return result
}

context(HttpServletRequest)
fun response(content: BuilderWithTags.() -> Unit): String {
    return if (isHtmxRequest()) {
        htmxResponse(content)
    } else {
        pageResponse(content)
    }.also { println(it) }
}

context(HttpServletRequest)
private fun isHtmxRequest(): Boolean {
    val hxRequestHeader = getHeader("HX-Request") ?: "false"
    return hxRequestHeader == "true"
}

context(HttpServletRequest)
private fun htmxResponse(content: BuilderWithTags.() -> Unit) = noRoot {
    Htmx(content)
}

context(HttpServletRequest)
private fun pageResponse(content: BuilderWithTags.() -> Unit) = html {
    Page(content)
}