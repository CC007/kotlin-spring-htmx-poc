package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest

import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.page.App
import com.github.cc007.kotlinspringhtmxpoc.utils.html.*
import com.github.cc007.kotlinspringhtmxpoc.utils.html.head.script
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Index {

    @GetMapping("/")
    @ResponseBody
    fun index(): String {
        return html {
            head {
                script("https://cdn.tailwindcss.com")
            }
            body {
                App()
            }
        }
    }
}
