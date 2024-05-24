package javaLibrary;
import java.util.List;
import java.util.Map;

public interface ILibraryManager {
    List<Book> getSingleCopyBooks();
    List<Book> getAll();
    boolean addBook(Book book);
    List<Book> searchBooks(String searchQuery);
}
