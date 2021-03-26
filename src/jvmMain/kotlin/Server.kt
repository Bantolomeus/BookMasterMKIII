import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port) {
        routing {
            get("/") {
                call.respondText("Hello there")
            }
        }
    }.start(wait = true)
}
