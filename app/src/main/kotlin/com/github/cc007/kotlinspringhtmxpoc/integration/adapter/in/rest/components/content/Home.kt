package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content

import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import com.github.cc007.kotlinspringhtmxpoc.utils.response
import com.github.cc007.kotlinspringhtmxpoc.utils.withRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Home {

    @GetMapping("/")
    @ResponseBody
    fun home(request: HttpServletRequest): String = withRequest(request) {
        model["title"] = "Homepage"
        response {
            p { text("Lorum ipsum dolor...") }
        }
    }
}
