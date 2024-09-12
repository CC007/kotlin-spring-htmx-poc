package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content

import com.github.cc007.dsl.html.HtmlTag.Builder
import com.github.cc007.dsl.html.tag.body.li
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.dsl.html.tag.body.ul
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import com.github.cc007.kotlinspringhtmxpoc.utils.response
import com.github.cc007.kotlinspringhtmxpoc.utils.withRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException


class Content {
    @GetMapping("/{page}")
    @ResponseBody
    fun home(request: HttpServletRequest, @PathVariable("page") page: String): String =
        withRequest(request) {
            val contentPageType = fromPage(page)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            model["title"] = contentPageType.title
            response {
                (contentPageType::pageContent)()
            }
        }
}

enum class ContentPageType(
    val page: String,
    val title: String,
    val pageContent: Builder.() -> Unit
) {
    BIO("bio", "Biography", Builder::Bio),
    PORTFOLIO("portfolio", "Portfolio", Builder::Portfolio),
    SOCIALS("socials", "Socials", Builder::Socials),
}

private fun fromPage(page: String): ContentPageType? =
    ContentPageType.entries.firstOrNull { it.page == page }

fun Builder.Bio() {
    p { text("This is the bio section.") }
}

fun Builder.Portfolio() {
    p { text("This is the portfolio section.") }
    ul {
        li {
            hxGet("/bigben", REF_CONTENT) {
                attribute("preload" to "mouseover")
                attribute("preload-images" to "true")
                text("Big Ben")
            }
        }
    }
}

fun Builder.Socials() {
    p { text("This is the socials section.") }
}

