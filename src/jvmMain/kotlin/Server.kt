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
    embeddedServer(Netty, port) {

        val bookRepo = BookRepository()
        val updateRepo = BookUpdatesRepository()

        val bookList = mutableListOf(
            BookListItem("Ensel und Krete", "Walter Moers", 255, 255, "04/02/2018", 0)
        )

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
            get("/") {
                call.respondText(
                    this::class.java.classLoader.getResource("index.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/") {
                resources("")
            }
            get("/bookUpdates") {
                call.respondText(
                    this::class.java.classLoader.getResource("updates.html")!!.readText(),
                    ContentType.Text.Html
                )
            }
            static("/bookUpdates") {
                resources("")
            }
            route(BookUpdateItem.path) {
                get {
                    call.respond(updateRepo.getBookUpdates())
//                    call.respond(bookList)
                }
            }
            route(BookListItem.path) {
                get {
                    call.respond(bookRepo.getBooks())
//                    call.respond(bookList)
                }
                post {
                    bookList += call.receive<BookListItem>()
                    call.respond(HttpStatusCode.OK)
                }
                delete("/{id}") {
                    val id = call.parameters["id"]?.toInt() ?: error("Invalid delete request")
                    bookList.removeIf { it.id == id }
                    call.respond(HttpStatusCode.OK)
                }
            }
        }
    }.start(wait = true)
}
