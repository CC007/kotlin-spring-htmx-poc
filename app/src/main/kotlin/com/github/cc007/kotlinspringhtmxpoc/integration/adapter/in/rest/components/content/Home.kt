package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.tag.body.p

fun BuilderWithTags.Home() {
    p {
        text("Lorum ipsum dolor...")
    }
}