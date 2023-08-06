package org.example.application.templates

import kotlinx.html.*

fun HTML.index() {
    head {
        title("Memes Hunter")
        styleLink(url = "/static/css/normalize.css")
    }
    body {
        div {
            id = "root"
        }
        script(src = "/static/memesHunter.js") {}
    }
}