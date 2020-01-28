package com.sleepman.restdoctest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestdoctestApplication

fun main(args: Array<String>) {
    runApplication<RestdoctestApplication>(*args)
}
