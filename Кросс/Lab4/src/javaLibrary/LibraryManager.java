package javaLibrary;

import java.io.*;
import java.util.*;

public class LibraryManager implements ILibraryManager{
    private List<Book> books;
    private String fileName;
    public LibraryManager(String fileName) {
        this.books = new ArrayList<>();
        this.fileName = fileName;
        if ((new File(fileName)).exists()){
            this.reloadData();
        }
    }
    @Override
    public List<Book> getSingleCopyBooks() {
        List<Book> booksBySingleCopy = new ArrayList<>();
        for (Book book : books) {
            if (book.getCopies()==1) {
                booksBySingleCopy.add(book);
            }
        }
        return booksBySingleCopy;
    }

    @Override
    public List<Book> getAll() {
        reloadData();
        return books;
    }

    @Override
    public boolean addBook(Book book) {
        int id = 0;
        reloadData();
        if(this.books.size()>0) {
            OptionalInt maxId = this.books.stream()
                    .mapToInt(c -> c.getId()).max();
            if (maxId != null) {

                id = maxId.getAsInt();
            }
        }
        book.setId(id+1);
        books.add(book);
        try {
            save();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<Book> searchBooks(String searchQuery) {
        List<Book> booksByQuery = new ArrayList<>();
        for (Book book : books) {
            if (Objects.equals(book.getAuthorLastName(), searchQuery) ||
                    book.getTitle().toLowerCase().contains(searchQuery.toLowerCase())) {
                booksByQuery.add(book);
            }
        }
        return booksByQuery;
    }
    private void reloadData() {
        if ((new File(fileName)).exists()) {
            try (FileInputStream fileInputStream =
                         new FileInputStream(fileName)) {
                try (ObjectInputStream objectInputStream =
                             new ObjectInputStream(fileInputStream)) {
                    books = (List<Book>) objectInputStream
                            .readObject();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void save() throws IOException {
        try(FileOutputStream fileOutputStream =
                    new FileOutputStream(fileName)){
            try(ObjectOutputStream objectOutputStream =
                        new ObjectOutputStream(fileOutputStream)){
                objectOutputStream.writeObject(this.books);
            }
        }
    }
}
