import kotlinx.browser.document
import react.dom.*
import kotlinx.css.*
import styled.*

val finishedBooks = listOf(
    DBook("Fablehaven", "Brandon Mull", 359, "https://upload.wikimedia.org/wikipedia/en/0/08/Fablehaven.jpg"),
    DBook("Fablehaven Rise of the Evening Star", "Brandon Mull", 441, "https://upload.wikimedia.org/wikipedia/en/3/31/Fablehaven_Rise_of_the_Evening_Star.jpg"),
    DBook("Fablehaven Grip of the Shadow Plague", "Brandon Mull", 487, "https://upload.wikimedia.org/wikipedia/en/2/29/Fablehaven_Grip_of_the_Shadow_Plague.jpg"),
    DBook("Fablehaven Secrets of the Dragon Sanctuary", "Brandon Mull", 535, "https://upload.wikimedia.org/wikipedia/en/5/58/Fablehaven_Secrets_of_the_Dragon_Sanctuary.jpg"),
    DBook("Fablehaven Keys to the Demon Prison", "Brandon Mull", 593, "https://upload.wikimedia.org/wikipedia/en/e/ef/Fablehaven_Keys_to_the_Demon_Prison.jpg")
)

val toReadBooks = listOf(
    DBook("Beyonders a World without Heroes", "Brandon Mull", 454, "https://upload.wikimedia.org/wikipedia/en/8/84/BeyondersAWorldWithoutHeroes.jpg")
)

fun main() {

    render(document.getElementById("root")) {
        h1 { +"Hello BookMaster user."
        }
        div {
            h3 { +"Expect a lot of books to read. Like this titles that I already read."
            }
            for(book in finishedBooks) {
                p {
                    +"${book.title}, with ${book.pages} pages and written by ${book.author}"
                }
            }
        }
        
        styledDiv {
            css {
                position = Position.relative
            }
            h3 { +"At the end after many years you will have something like this"
            }
            img {
               attrs {
                   src = "https://storage.googleapis.com/yk-cdn/photos/pdp/thibaud-poirier/trinity-college-library.jpg"
               }
            }
            img {
                attrs {
                    src = "https://upload.wikimedia.org/wikipedia/en/0/08/Fablehaven.jpg"
                }
            }
        }
    }
}
