import react.*
import react.dom.*
import styled.styledDiv


external interface AppState: RState {
    var currentBook: Book?
    var unreadBooks: List<Book>
    var finishedBooks: List<Book>
}

@Suppress("NON_EXPORTABLE_TYPE")
@ExperimentalJsExport
@JsExport
class App : RComponent<RProps, AppState>() {

    override fun AppState.init() {
        finishedBooks = listOf(
            DBook("Fablehaven", "Brandon Mull", 359, "https://upload.wikimedia.org/wikipedia/en/0/08/Fablehaven.jpg"),
            DBook("Fablehaven Rise of the Evening Star", "Brandon Mull", 441, "https://upload.wikimedia.org/wikipedia/en/3/31/Fablehaven_Rise_of_the_Evening_Star.jpg"),
            DBook("Fablehaven Grip of the Shadow Plague", "Brandon Mull", 487, "https://upload.wikimedia.org/wikipedia/en/2/29/Fablehaven_Grip_of_the_Shadow_Plague.jpg"),
            DBook("Fablehaven Secrets of the Dragon Sanctuary", "Brandon Mull", 535, "https://upload.wikimedia.org/wikipedia/en/5/58/Fablehaven_Secrets_of_the_Dragon_Sanctuary.jpg"),
            DBook("Fablehaven Keys to the Demon Prison", "Brandon Mull", 593, "https://upload.wikimedia.org/wikipedia/en/e/ef/Fablehaven_Keys_to_the_Demon_Prison.jpg")
        )

        unreadBooks = listOf(
            DBook("Beyonders a World without Heroes", "Brandon Mull", 454, "https://upload.wikimedia.org/wikipedia/en/8/84/BeyondersAWorldWithoutHeroes.jpg")
        )
    }

    override fun RBuilder.render() {
        h1 { +"Hello BookMaster user."
        }
        div {
            h3 { +"Expect a lot of books to read. Like this titles that I already read."
            }
            bookList {
                books = state.finishedBooks
                selectedBook = state.currentBook
                onSelectedBook = { book ->
                    setState {
                        currentBook = book
                    }
                }
            }

            h3 { +"Or books to read in the future."
            }
            bookList {
                books = state.unreadBooks
                selectedBook = state.currentBook
                onSelectedBook = { book ->
                    setState {
                        currentBook = book
                    }
                }
            }
        }

        state.currentBook?.let { currentBook ->
            bookCase {
                book = currentBook
                unreadBook = currentBook in state.unreadBooks
                onReadButtonPressed = {
                    if (book in state.unreadBooks) {
                        setState {
                            unreadBooks -= book
                            finishedBooks += book
                        }
                    } else {
                        setState {
                            finishedBooks -= book
                            unreadBooks += book
                        }
                    }
                }
            }
        }
        styledDiv {
        }
    }
}

fun RBuilder.bookList(handler: BookListProps.() -> Unit): ReactElement {
    return child(BookList::class) {
        this.attrs(handler)
    }
}
