import kotlinx.serialization.Serializable

@Serializable
data class BookUpdateItem(val name: String = "Placeholder",
                          val pagesRead: Int = 0,
                          val date: String = "noDate") {
//    val id: Int = name.hashCode() + date.hashCode()

    companion object {
        const val path = "/bookUpdates"
    }
}
