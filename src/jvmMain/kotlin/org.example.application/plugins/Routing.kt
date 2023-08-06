package org.example.application.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import kotlinx.html.HTML
import org.example.application.templates.index

fun Application.configureRouting() {
    routing {
        configureApi()

        // must be last
        configureWeb()
    }
}


fun Routing.configureWeb() {
    get("*") {
        call.respondHtml(HttpStatusCode.OK, HTML::index)
    }

    static("/static") {
        resources()
    }
}

fun Routing.configureApi() {
    route("/api/v1") {
        configurePublicApi()
        configureAuthorizedApi()
    }
}

fun Route.configurePublicApi() {
    // TODO: configure public api
}

fun Route.configureAuthorizedApi() {
    // TODO: configure public api
}