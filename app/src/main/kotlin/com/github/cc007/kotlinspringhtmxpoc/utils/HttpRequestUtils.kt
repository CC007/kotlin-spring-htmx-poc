package com.github.cc007.kotlinspringhtmxpoc.utils

import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.isHtmxRequest(): Boolean {
    val hxRequestHeader = getHeader("HX-Request") ?: "false"
    return hxRequestHeader == "true"
}