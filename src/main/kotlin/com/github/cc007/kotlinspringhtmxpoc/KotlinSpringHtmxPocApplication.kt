package com.github.cc007.kotlinspringhtmxpoc

import org.springframework.boot.Banner.Mode.OFF
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringPocApplication

fun main(args: Array<String>) {
    runApplication<KotlinSpringPocApplication>(*args) {
        setBannerMode(OFF)
    }
}
