package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.Home
import com.github.cc007.kotlinspringhtmxpoc.utils.response
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Index {

    @GetMapping("/")
    @ResponseBody
    fun index(request: HttpServletRequest): String {
        return request.response("Home", BuilderWithTags::Home)
    }
}