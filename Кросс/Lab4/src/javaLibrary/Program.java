package javaHospital;

import javaLibrary.Book;
import javaLibrary.ILibraryManager;
import javaLibrary.LibraryManager;

import java.util.*;
public class Program {
    private static ILibraryManager manager;

    public static void main(String[] args) {
        manager = new LibraryManager("book.txt");
        List<Book> books = manager.getAll();

        Scanner input = new Scanner(System.in);

        int choice;

        while (true) {
            System.out.println("Оберіть потрібну дію:");
            System.out.println("1 - переглянути всі записи");
            System.out.println("2 - додати книгу ");
            System.out.println("3 - переглянути книги в єдиному примірнику");
            System.out.println("4 - переглянути книги за прізвищем автора чи назвоюю (або її частиною)");
            System.out.println("0 - завершити роботу програми");
            choice = Integer.parseInt(input.nextLine());
            if (choice == 0) break;
            switch (choice) {
                case 1:

                    books = manager.getAll();

                    for (Book c : books) {
                        System.out.println(c);
                    }
                    break;
                case 2:
                    Book book = inputNewBook();
                    manager.addBook(book);
                    break;
                case 3:
                    books = manager.getSingleCopyBooks();
                    for (Book c : books) {
                        System.out.println(c);
                    }
                    break;
                case 4:
                    System.out.println("Задайте прізвище автора чи назву книги:");
                    String  query= input.nextLine();
                    books = manager.searchBooks(query);
                    for (Book c : books) {
                        System.out.println(c);
                    }
                    break;
            }
        }
    }

    private static Book inputNewBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть дані книги.");
        System.out.println("Шифр книги:");
        String code = scanner.nextLine();
        System.out.println("Назва:");
        String title = scanner.nextLine();
        System.out.println("Прізвище автора:");
        String authorLastName = scanner.nextLine();
        System.out.println("Рік видання:");
        int year = scanner.nextInt();
        System.out.println("Кількість примірників:");
        int copiesCount = scanner.nextInt();
        return new Book(code, title, authorLastName, year, copiesCount);
    }
}