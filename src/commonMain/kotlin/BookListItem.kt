import kotlinx.serialization.Serializable

@Serializable
data class BookListItem(val title: String,
                        val author: String,
                        val pagesTotal: Int,
                        var currentPage: Int,
                        val dateStarted: String,
                        var readTime: Int) {
    val id: Int = title.hashCode()

    companion object {
        const val path = "/books"
    }
}
