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

val App = functionalComponent<RProps> { _ ->
    val (shoppingList, setShoppingList) = useState(emptyList<ShoppingListItem>())

    useEffect(dependencies = listOf()) {
        scope.launch {
            setShoppingList(getShoppingList())
        }
    }

    h1 {
        +"Full-Stack Shopping List"
    }
    ul {
        shoppingList.sortedByDescending(ShoppingListItem::priority).forEach { item ->
            li {
                key = item.toString()
                +"[${item.priority}] ${item.desc} "
            }
        }
    }
}
