import kotlinext.js.jsObject
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h1
import react.dom.li
import react.dom.ul

private val scope = MainScope()

val App = functionalComponent<RProps> { _ ->
    val (bookList, setBookList) = useState(emptyList<BookListItem>())
    val (bookUpdateList, setBookUpdateList) = useState(emptyList<BookUpdateItem>())

    useEffect(dependencies = listOf()) {
        scope.launch {
            setBookList(getBookList())
            setBookUpdateList(getBookUpdateList())
        }
    }

    h1 {
        +"All read books"
    }
    ul {
        bookList.sortedByDescending(BookListItem::dateStarted).forEach { item ->
            li {
                key = item.toString()
                +"${item.title}, ${item.author}, ${item.pagesTotal}, ${item.currentPage}, ${item.dateStarted}, ${item.readTime}"

//                attrs.onClickFunction = {
//                    scope.launch {
//                        deleteBookListItem(item)
//                        setBookList(getBookList())
//                    }
//                }
            }
        }
//        shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
//            li {
//                key = item.toString()
//                +"[${item.priority}] ${item.desc} "
//
//                attrs.onClickFunction = {
//                    scope.launch {
//                        deleteShoppingListItem(item)
//                        setShoppingList(getShoppingList())
//                    }
//                }
//            }
//        }
    }
    h1 {
        +"All book updates"
    }
    ul {
        bookUpdateList.sortedByDescending(BookUpdateItem::pagesRead).forEach { item ->
            li {
                key = item.toString()
                +"${item.name}, ${item.date}, ${item.pagesRead}"
            }
        }
    }

    child(
        InputComponent,
        props = jsObject {
            onSubmit = { input ->
//                val cartItem = ShoppingListItem(input.replace("!", ""), input.count { it == '!' })
                val splittedInput = input.split(",")
                val bookItem = BookListItem(splittedInput[0], splittedInput[1], splittedInput[2].toInt(), splittedInput[3].toInt(),
                    splittedInput[4], splittedInput[5].toInt())
                scope.launch {
                    addBookListItem(bookItem)
                    setBookList(getBookList())
                }
            }
        }
    )
}
