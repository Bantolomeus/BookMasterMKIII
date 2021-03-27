import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8081

    val shoppingList = mutableListOf(
        ShoppingListItem("Cucumbers \uD83E\uDD52", 1),
        ShoppingListItem("Tomatoes \uD83C\uDF45", 2),
        ShoppingListItem("Orange Juice \uD83C\uDF4A", 3)
    )

    embeddedServer(Netty, port) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            route(ShoppingListItem.path) {
                get {
                    call.respond(shoppingList)
                }
                post {
                    shoppingList += call.receive<ShoppingListItem>()
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    shoppingList.removeIf { it.id == id}
                    call.respond(HttpStatusCode.OK)
                }
            }
            route("/") {
                get {
                    call.respondText(
                        this::class.java.classLoader.getResource("index.html")!!.readText(),
                        ContentType.Text.Html
                    )
                }
                static {
                    resource("")
                }
            }

        }
    }.start(wait = true)
}
