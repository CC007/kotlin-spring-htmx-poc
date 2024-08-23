package com.github.cc007.dsl.html

import kotlin.reflect.KProperty

interface Invokable {
    operator fun invoke(configure: HtmlTag.Builder.() -> Unit = {})
}

object BuilderDelegate {
    operator fun getValue(thisRef: HtmlTag.Builder, property: KProperty<*>): Invokable {
        val tag = thisRef.tag(property.name)
        return object : Invokable {
            override fun invoke(configure: HtmlTag.Builder.() -> Unit) {
                thisRef.replaceTag(tag, property.name, configure)
            }
        }
    }
}

object SelfClosingBuilderDelegate {
    operator fun getValue(thisRef: HtmlTag.Builder, property: KProperty<*>): Invokable {
        val tag = thisRef.tag(property.name) { selfClosing = true }
        return object : Invokable {
            override fun invoke(configure: HtmlTag.Builder.() -> Unit) {
                thisRef.replaceTag(tag, property.name) {
                    selfClosing = true
                    configure()
                }
            }
        }
    }
}