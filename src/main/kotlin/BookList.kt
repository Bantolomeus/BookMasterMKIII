import kotlinx.css.Cursor
import kotlinx.css.cursor
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css
import styled.styledP

external interface BookListProps: RProps {
    var books: List<Book>
    var selectedBook: Book?
    var onSelectedBook: (Book) -> Unit
}

@JsExport
class BookList: RComponent<BookListProps, RState>() {
    override fun RBuilder.render() {
        for (book in props.books) {
            styledP {
                css {
                    cursor = Cursor.pointer
                }
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
