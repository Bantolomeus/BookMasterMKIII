import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.RProps
import react.dom.h1
import react.dom.li
import react.dom.ul
import react.functionalComponent
import react.useEffect
import react.useState

private val scope = MainScope()

val UpdateView = functionalComponent<RProps> { _ ->
    val (bookUpdateList, setBookUpdateList) = useState(emptyList<BookUpdateItem>())

    useEffect(dependencies = listOf()) {
        scope.launch {
            setBookUpdateList(getBookUpdateList())
        }
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
}
