import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.util.*

const val BOOK_FILE = "books.json"

class BookRepository(private val bookFile: String = BOOK_FILE) {
    private val objectMapper = jacksonObjectMapper()

//    fun saveBooks(books: List<BookListItem>): List<BookListItem> {
//        objectMapper.writeValue(File(bookFile), books)
//        return books
//    }
//
//    fun updateBook(book: BookDTO): List<BookDTO> {
//        val booksExceptBook = getAllBooksExcept(book.name)
//        return saveBooks(booksExceptBook + book)
//    }
//
//    fun saveBookIfItNotExists(book: BookDTO): BookDTO {
//        val existingBook = getBookByName(book.name)
//        if (existingBook == null) {
//            val books = getBooks()
//            book.dateStarted = dateFormat.format(Date())
//            objectMapper.writeValue(File(bookFile), books + book)
//            return book
//        }
//
//        return existingBook
//    }

    fun getBooks(): List<BookListItem> {
        return try {
            objectMapper.readValue(File(bookFile))
        } catch (exception: Exception) {
            println("ERROR[getBooks]: The books file does not exists. \n Stacktrace: \n $exception")
            emptyList()
        }
    }

//    fun getBookByName(bookName: String): BookDTO? {
//        return try {
//            objectMapper.readValue<List<BookDTO>>(File(bookFile))
//                .firstOrNull { it.name == bookName }
//        } catch (exception: Exception) {
//            println("ERROR[getBookByName]: The books file does not exists. \n Stacktrace: \n $exception")
//            null
//        }
//    }
//
//    fun getAllBooksExcept(bookName: String): List<BookDTO> {
//        return try {
//            objectMapper.readValue<List<BookDTO>>(File(bookFile)).filter { it.name != bookName }
//        } catch (exception: Exception) {
//            println("ERROR[getBooks]: The books file does not exists. \n Stacktrace: \n $exception")
//            emptyList()
//        }
//    }
}
