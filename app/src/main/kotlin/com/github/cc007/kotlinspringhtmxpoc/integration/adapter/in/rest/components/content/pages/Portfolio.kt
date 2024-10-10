package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.dsl.html.tag.body.li
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.dsl.html.tag.body.ul
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.common.hxGet
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.ContentPageType
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.REF_CONTENT
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages.portfolio.BigBen
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import com.github.cc007.kotlinspringhtmxpoc.utils.response
import com.github.cc007.kotlinspringhtmxpoc.utils.withRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.server.ResponseStatusException
import kotlin.reflect.KFunction2

fun BuilderWithTags.Portfolio() {
    p { text("This is the portfolio section.") }
    ul {
        classes("list-disc list-inside")
        li {
            hxGet("/portfolio/bigben", REF_CONTENT) {
                attribute("preload" to "mouseover")
                attribute("preload-images" to "true")
                text("Big Ben")
            }
        }
    }
}

@Controller
class Portfolio {
    @GetMapping("/portfolio/{page}")
    @ResponseBody
    fun portfolioPage(request: HttpServletRequest, @PathVariable("page") page: String): String =
        withRequest(request) {
            val contentPageType = ContentPageType.PORTFOLIO
            val portfolioPageType = fromPortfolioPage(page)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            model["title"] = "${contentPageType.title} - ${portfolioPageType.title}"
            model["contentPage"] = contentPageType
            response {
                val portfolioContent = portfolioPageType.portfolioPageContent
                portfolioContent(this@withRequest, this)
            }
        }
}

enum class PortfolioPageType(
    val page: String,
    val title: String,
    val portfolioPageContent: KFunction2<HttpServletRequest, BuilderWithTags, Unit>
) {
    BIGBEN("bigben", "Big Ben demo", BuilderWithTags::BigBen),
}

private fun fromPortfolioPage(page: String): PortfolioPageType? =
    PortfolioPageType.entries.firstOrNull { it.page == page }

