package kotlinLibrary
import java.io.*
import java.util.*

class LibraryManager(private val fileName: String) : ILibraryManager {
    private var books: MutableList<Book> = mutableListOf()

    init {
        if (File(fileName).exists()) {
            reloadData()
        }
    }

    override fun getSingleCopyBooks(): List<Book> {
        return books.filter { it.copiesCount == 1 }
    }

    override fun getAll(): List<Book> {
        reloadData()
        return books
    }

    override fun addBook(book: Book): Boolean {
        reloadData()
        val id = books.maxByOrNull { it.id }?.id ?: 0
        book.id = id + 1
        books.add(book)
        try {
            save()
            return true
        } catch (e: IOException) {
            return false
        }
    }

    override fun searchBooks(searchQuery: String): List<Book> {
        val queryLower = searchQuery.toLowerCase()
        return books.filter {
            it.authorLastName.equals(queryLower, ignoreCase = true) ||
                    it.title.toLowerCase().contains(queryLower)
        }
    }

    private fun reloadData() {
        if (File(fileName).exists()) {
            ObjectInputStream(FileInputStream(fileName)).use { objectInputStream ->
                books = objectInputStream.readObject() as MutableList<Book>
            }
        }
    }


    private fun save() {
        ObjectOutputStream(FileOutputStream(fileName)).use { objectOutputStream ->
            objectOutputStream.writeObject(books)
        }
    }
}
