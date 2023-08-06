package org.example.application

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.example.application.plugins.configureRouting
import org.example.application.plugins.configureSecurity
import org.example.application.plugins.configureSerialization


fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        extracted()
    }.start(wait = true)
}

private fun Application.extracted() {
    configureRouting()
    configureSecurity()
    configureSerialization()
}