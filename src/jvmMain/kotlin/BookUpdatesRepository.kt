import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File

const val BOOK_UPDATES_FILE = "bookUpdates.json"

class BookUpdatesRepository(private val bookUpdatesFile: String = BOOK_UPDATES_FILE) {
    private val objectMapper = ObjectMapper()

//    init {
//        if (!File(bookUpdatesFile).exists()) {
//            objectMapper.writeValue(File(bookUpdatesFile), BookUpdatesFileDTO())
//        }
//    }

//    fun saveBookUpdate(bookUpdate: BookUpdatesFileDTO): BookUpdatesFileDTO {
//        objectMapper.writeValue(File(bookUpdatesFile), bookUpdate)
//        return bookUpdate
//    }

    fun getBookUpdates(): List<BookUpdateItem> {
        return try {
            objectMapper.readValue(File(bookUpdatesFile))
        } catch (exception: Exception) {
            println("ERROR[getBookUpdates]: The bookUpdates file does not exists. \n Stacktrace: \n $exception")
            return emptyList()
        }
    }

//    fun sortBookUpdates(): BookUpdatesFileDTO {
//        return try {
//            val bookUpdates = BookUpdatesFileDTO(objectMapper.readValue(File(bookUpdatesFile), BookUpdatesFileDTO::class.java)
//                .bookUpdates
//                .asSequence()
//                .sortedByDescending { it.date }.toMutableList())
//            objectMapper.writeValue(File(bookUpdatesFile), bookUpdates)
//            bookUpdates
//        } catch (exception: Exception) {
//            BookUpdatesFileDTO()
//        }
//    }
//
//    fun getUpdateFromToday(bookName: String): BookUpdateOutputDTO? {
//        return getBookUpdates()?.bookUpdates?.firstOrNull { it.date == dateFormat.format(Date()) && it.name == bookName }
//    }
}
