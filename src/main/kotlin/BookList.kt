import kotlinx.browser.window
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.p

external interface BookListProps: RProps {
    var books: List<Book>
    var selectedBook: Book?
    var onSelectedBook: (Book) -> Unit
}

@JsExport
class BookList: RComponent<BookListProps, RState>() {
    override fun RBuilder.render() {
        for (book in props.books) {
            p {
                attrs {
                    onClickFunction = {
                        props.onSelectedBook(book)
                    }
                }
                if (book == props.selectedBook) {
                    +"▶ "
                }
                +"${book.title}, with ${book.pages} pages and written by ${book.author}"
            }
        }
    }
}
