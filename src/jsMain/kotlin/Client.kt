import react.create
import react.dom.client.createRoot
import web.dom.document


fun main() {
    val container = document.getElementById("root")!!

    val welcome = Welcome.create {
        name = "Kotlin/JS"
    }
    createRoot(container).render(welcome)
}