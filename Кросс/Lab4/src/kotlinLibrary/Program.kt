package kotlinLibrary

import javaLibrary.Book
import javaLibrary.ILibraryManager
import javaLibrary.LibraryManager
import java.io.File
import java.util.*

object Program {
    private var manager: ILibraryManager? = null

    @JvmStatic
    fun main(args: Array<String>) {
        manager = LibraryManager("book2.txt")
        var books = manager?.getAll()

        val input = Scanner(System.`in`)

        var choice: Int

        while (true) {
            println("Оберіть потрібну дію:")
            println("1 - переглянути всі записи")
            println("2 - додати книгу")
            println("3 - переглянути книги в єдиному примірнику")
            println("4 - переглянути книги за прізвищем автора чи назвоюю (або її частиною)")
            println("0 - завершити роботу програми")
            choice = input.nextLine().toInt()

            if (choice == 0) break

            when (choice) {
                1 -> {
                    books = manager?.getAll()
                    books?.forEach { println(it) }
                }
                2 -> {
                    val book = inputNewBook()
                    manager?.addBook(book)
                }
                3 -> {
                    books = manager?.getSingleCopyBooks()
                    books?.forEach { println(it) }
                }
                4 -> {
                    println("Задайте прізвище автора чи назву книги:")
                    val query = input.nextLine()
                    books = manager?.searchBooks(query)
                    books?.forEach { println(it) }
                }
            }
        }
    }

    private fun inputNewBook(): Book {
        val scanner = Scanner(System.`in`)
        println("Введіть дані книги.")
        println("Шифр книги:")
        val code = scanner.nextLine()
        println("Назва:")
        val title = scanner.nextLine()
        println("Прізвище автора:")
        val authorLastName = scanner.nextLine()
        println("Рік видання:")
        val year = scanner.nextInt()
        println("Кількість примірників:")
        val copiesCount = scanner.nextInt()
        return Book(code, title, authorLastName, year, copiesCount)
    }
}
