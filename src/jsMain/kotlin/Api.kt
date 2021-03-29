import io.ktor.http.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

import kotlinx.browser.window

val endpoint = window.location.origin // only needed until https://github.com/ktorio/ktor/issues/1695 is resolved

val jsonClient = HttpClient {
    install(JsonFeature) { serializer = KotlinxSerializer() }
}

suspend fun getShoppingList(): List<ShoppingListItem> {
    return jsonClient.get(endpoint + ShoppingListItem.path)
}

suspend fun addShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.post<Unit>(endpoint + ShoppingListItem.path) {
        contentType(ContentType.Application.Json)
        body = shoppingListItem
    }
}

suspend fun deleteShoppingListItem(shoppingListItem: ShoppingListItem) {
    jsonClient.delete<Unit>(endpoint + ShoppingListItem.path + "/${shoppingListItem.id}")
}

suspend fun getBookList(): List<BookListItem> {
    return jsonClient.get(endpoint + BookListItem.path)
}

suspend fun addBookListItem(bookListItem: BookListItem) {
    jsonClient.post<Unit>(endpoint + BookListItem.path) {
        contentType(ContentType.Application.Json)
        body = bookListItem
    }
}

suspend fun deleteBookListItem(bookListItem: BookListItem) {
    jsonClient.delete<Unit>(endpoint + BookListItem.path + "/${bookListItem.id}")
}

suspend fun getBookUpdateList(): List<BookUpdateItem> {
    return jsonClient.get(endpoint + BookUpdateItem.path)
}
