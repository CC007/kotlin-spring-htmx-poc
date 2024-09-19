package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.tag.body.p

fun BuilderWithTags.Bio() {
    p { text("This is the bio section.") }
}