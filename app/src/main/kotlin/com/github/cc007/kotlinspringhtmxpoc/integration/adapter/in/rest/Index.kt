package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest

import com.github.cc007.dsl.html.html
import com.github.cc007.dsl.html.noRoot
import com.github.cc007.dsl.html.tag.body
import com.github.cc007.dsl.html.tag.head
import com.github.cc007.dsl.html.tag.head.title
import com.github.cc007.dsl.html.tag.noroot.title
import com.github.cc007.dsl.html.tag.script
import com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.page.App
import com.github.cc007.kotlinspringhtmxpoc.utils.isHtmxRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class Index {

    @GetMapping("/")
    @ResponseBody
    fun index(request: HttpServletRequest): String {
        return if (request.isHtmxRequest()) htmxResponse() else pageResponse()
    }

    private fun htmxResponse(): String = noRoot {
        title("Hello World")
    }

    /**
     * html using configuration. This doesn't enforce the use of the head and body tag
     */
    private fun pageResponse() = html {
        head {
            title("Hello World")
            script("https://unpkg.com/htmx.org@1.9.12/dist/htmx.min.js")
            script("https://unpkg.com/htmx.org@1.9.12/dist/ext/response-targets.js")
            script("https://unpkg.com/htmx.org@1.9.12/dist/ext/preload.js")
            script("https://cdn.tailwindcss.com")
        }
        body {
            App()
        }
    }

    /**
     * html using head and body, with explicit parameter names
     */
//  private fun pageResponse2() = html(
//      head = {
//          title("Hello World")
//          script("https://unpkg.com/htmx.org@1.9.12/dist/htmx.min.js")
//          script("https://unpkg.com/htmx.org@1.9.12/dist/ext/response-targets.js")
//          script("https://unpkg.com/htmx.org@1.9.12/dist/ext/preload.js")
//          script("https://cdn.tailwindcss.com")
//      },
//      body = {
//          App()
//      }
//  )


    /**
     * html using head and body, with implicit parameter names
     */
//  private fun pageResponse3() = html({
//      title("Hello World")
//      script("https://unpkg.com/htmx.org@1.9.12/dist/htmx.min.js")
//      script("https://unpkg.com/htmx.org@1.9.12/dist/ext/response-targets.js")
//      script("https://unpkg.com/htmx.org@1.9.12/dist/ext/preload.js")
//      script("https://cdn.tailwindcss.com")
//  }, {
//      App()
//  })
}