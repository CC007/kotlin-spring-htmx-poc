package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content


import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.tag.body.h1
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import com.github.cc007.kotlinspringhtmxpoc.utils.response
import com.github.cc007.kotlinspringhtmxpoc.utils.withRequest
import jakarta.servlet.RequestDispatcher.ERROR_STATUS_CODE
import jakarta.servlet.http.HttpServletRequest
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.HttpStatus.resolve
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CustomErrorController : ErrorController {
//    private val menuItemService: MenuItemService? = null

    @RequestMapping("/error")
    @ResponseBody
    fun handleError(request: HttpServletRequest): String = withRequest(request) {
        //model.addAttribute("menuItems", menuItemService.getMenuItems(null))
        val status = (request.getAttribute(ERROR_STATUS_CODE) as? Int)
            ?.let(::resolve)
            ?: INTERNAL_SERVER_ERROR
        setTitleFromStatus(status)
        response { fromStatus(status) }
    }


    context(HttpServletRequest)
    private fun setTitleFromStatus(status: HttpStatus) {
        model["title"] = when (status) {
            HttpStatus.NOT_FOUND -> "404 - Page not found"
            else -> "500 - Unknown Error"
        }
    }

    context(HttpServletRequest)
    private fun BuilderWithTags.fromStatus(status: HttpStatus) {
        return when (status) {
            HttpStatus.NOT_FOUND -> status404()
            else -> status500()
        }
    }

    context(HttpServletRequest)
    private fun BuilderWithTags.status404() {
        h1 { text("Page not found") }
        p { text("Sorry, but the page you were trying to view does not exist.") }
    }

    context(HttpServletRequest)
    private fun BuilderWithTags.status500() {
        h1 { text("Unknown Error") }
        p { text("Sorry, but the page you were trying to view could not be loaded due to an unknown error.") }
    }
}