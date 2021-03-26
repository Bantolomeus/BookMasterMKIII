import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledButton
import styled.styledDiv

external interface BookCaseProps: RProps {
    var book: Book
    var onReadButtonPressed: (Book) -> Unit
    var unreadBook: Boolean
}

@JsExport
class BookCase: RComponent<BookCaseProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"${props.book.title}, with ${props.book.pages} pages and written by ${props.book.author}"
            }
            styledButton {
                css {
                    display = Display.block
                    backgroundColor = if(props.unreadBook) Color.lightGreen else Color.orange
                }
                attrs {
                    onClickFunction = {
                        props.onReadButtonPressed(props.book)
                    }
                }
                if(props.unreadBook) {
                    +"Mark as read"
                }
                else {
                    +"Mark as unread"
                }
            }
            img {
                attrs {
                    src = props.book.coverUrl
                }
            }
        }
    }
}

fun RBuilder.bookCase(handler: BookCaseProps.() -> Unit): ReactElement {
    return child(BookCase::class) {
        this.attrs(handler)
    }
}
