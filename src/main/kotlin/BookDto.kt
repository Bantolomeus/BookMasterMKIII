external interface Book {
    val title: String
    val author: String
    val pages: Long
    val coverUrl: String
}

data class DBook(
    override val title: String,
    override val author: String,
    override val pages: Long,
    override val coverUrl: String
): Book

