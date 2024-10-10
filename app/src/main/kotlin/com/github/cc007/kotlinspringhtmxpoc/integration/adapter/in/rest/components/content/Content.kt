package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages.Bio
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages.Portfolio
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages.Socials
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

@Controller
class Content {
    @GetMapping("/{page}")
    @ResponseBody
    fun contentPage(request: HttpServletRequest, @PathVariable("page") page: String): String =
        withRequest(request) {
            val contentPageType = fromPage(page)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
            model["title"] = contentPageType.title
            model["contentPage"] = contentPageType
            response {
                val pageContent = contentPageType.pageContent
                pageContent()
            }
        }
}

enum class ContentPageType(
    val page: String,
    val title: String,
    val pageContent: BuilderWithTags.() -> Unit
) {
    BIO("bio", "Biography", BuilderWithTags::Bio),
    PORTFOLIO("portfolio", "Portfolio", BuilderWithTags::Portfolio),
    SOCIALS("socials", "Socials", BuilderWithTags::Socials),
}

private fun fromPage(page: String): ContentPageType? =
    ContentPageType.entries.firstOrNull { it.page == page }





