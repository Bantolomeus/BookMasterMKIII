import kotlinx.browser.window
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.p

external interface BookListProps: RProps {
    var books: List<Book>
}

external interface BookListState: RState {
    var selectedBook: Book?
}

@JsExport
class BookList: RComponent<BookListProps, BookListState>() {
    override fun RBuilder.render() {
        for (book in props.books) {
            p {
                attrs {
                    onClickFunction = {
                        setState {
                            selectedBook = book
                        }
                    }
                }
                if (book == state.selectedBook) {
                    +"▶ "
                }
                +"${book.title}, with ${book.pages} pages and written by ${book.author}"
            }
        }
    }
}
