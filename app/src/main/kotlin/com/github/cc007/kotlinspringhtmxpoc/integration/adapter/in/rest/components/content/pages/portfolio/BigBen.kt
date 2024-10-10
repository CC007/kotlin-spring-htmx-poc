package com.github.cc007.kotlinspringhtmxpoc.integration.adapter.`in`.rest.components.content.pages.portfolio

import com.github.cc007.dsl.html.BuilderWithTags
import com.github.cc007.dsl.html.attr.classes
import com.github.cc007.dsl.html.tag.ButtonType.BUTTON
import com.github.cc007.dsl.html.tag.body.button
import com.github.cc007.dsl.html.tag.body.div
import com.github.cc007.dsl.html.tag.body.h5
import com.github.cc007.dsl.html.tag.body.h6
import com.github.cc007.dsl.html.tag.body.img
import com.github.cc007.dsl.html.tag.body.p
import com.github.cc007.kotlinspringhtmxpoc.utils.model
import jakarta.servlet.http.HttpServletRequest
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.util.*

context(HttpServletRequest)
fun BuilderWithTags.BigBen() {
    val rawTime = getParameter("timestamp")
        ?.toLong()
        ?.let(Instant::ofEpochSecond) ?: Instant.now()
    model["timestamp"] = rawTime
        .atZone(ZoneOffset.UTC)
        .withMinute(0)
        .withSecond(0)
        .toInstant()

    model["offset"] = getParameter("offset")
        ?.toLong()
        ?: 0L
    val postCount = getParameter("viewportHeight")
        ?.toInt()
        ?.let { it / HEIGHT_PER_POST }
        ?: 20
    if (model["showDescription"] == true) {
        p { text("This is an example to demonstrate dynamic loading of content") }
    }
    for (postNr in 0 until postCount) {
        post(postNr, postCount)
    }
    // TODO
    // <#if postCount gt 0>
    //   <div hx-trigger="intersect once" hx-swap="outerHTML" hx-get="/bigben?offset=${nextOffset}&timestamp=${timestamp?c}">Load more...</div>
    // </#if>
}

context(HttpServletRequest)
private fun BuilderWithTags.post(postNr: Int, postCount: Int) {
    val timestamp = model["timestamp"] as Instant
    val offset = model["offset"] as Long
    val nextOffset = offset + postCount

    div {
        classes("max-w-xl", "w-full", "m-5", "shadow-md")
        div {
            classes("p-5")
            div {
                classes("flex", "flex-wrap", "items-start")
                div {
                    classes("w-full", "flex")
                    div {
                        classes("mr-2.5", "flex-none")
                        img {
                            classes("w-12", "h-12", "mr-3", "rounded-circle")
                            attribute("src" to "/images/bigben.jpeg")
                            attribute("alt" to "User Icon")
                        }
                    }
                    div {
                        classes("mr-2.5", "flex-none")
                        h5 {
                            classes("mx-auto", "font-bold", "text-black")
                            text("Big Ben")
                        }
                        h6 {
                            classes("mx-auto", "text-sm", "text-slate-500")
                            text("@the_bigben_clock")
                        }
                    }
                    div {
                        classes("ml-auto", "text-sm", "text-neutral-400")
                        val postTimestamp =
                            timestamp.epochSecond - 3600 * (postNr + nextOffset - postCount)
                        text(simpleDateFormat.format(postTimestamp * 1000L))
                    }
                }
                div {
                    classes("w-full")
                    p {
                        classes("mt-2.5", "text-black", "text-base")
                        val currentHour = timestamp
                            .atZone(ZoneOffset.UTC)
                            .minusHours(offset)
                            .hour
                        val bongs = ((currentHour-postNr-1)%12+12)%12+1
                        text {
                            (0 until bongs)
                                .map { "BONG" }
                                .joinToString(" ")
                        }
                    }
                }
            }
            div {
                listOf("Like", "Share", "Reply").forEach {
                    button(type = BUTTON) {
                        classes("m-2.5", "border", "border-blue-700", "text-sky-500")
                        classes("hover:bg-sky-500", "hover:text-white")
                        text(it)
                    }
                }
            }
        }
    }
}

private const val HEIGHT_PER_POST = 202
private val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH)

