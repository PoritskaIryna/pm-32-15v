package kotlinLibrary

interface ILibraryManager {
    fun getSingleCopyBooks(): List<Book>
    fun getAll(): List<Book>
    fun addBook(book: Book): Boolean
    fun searchBooks(searchQuery: String): List<Book>
}
