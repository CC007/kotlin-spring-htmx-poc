package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest

import com.github.cc007.kotlinspringhtmxpoc.utils.html.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Index {

    @GetMapping("/")
    @ResponseBody
    fun index(): String {
        val response = html {
            body {
                div()
                text("Hello World!")
            }
        }.stringify
        println(response)
        return response
    }
}
