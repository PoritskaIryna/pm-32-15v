package com.example.lab6;
import java.io.Serializable;

public class Book implements Serializable {
    private String callMark;
    private String publisher;
    private String title;
    private String authorSurname;
    private int yearOfPublication;
    private int copiesInStock;

    // Constructor
    public Book(String callMark,String publisher, String title, String authorSurname, int yearOfPublication, int copiesInStock) {
        this.callMark=callMark;
        this.publisher = publisher;
        this.title = title;
        this.authorSurname = authorSurname;
        this.yearOfPublication = yearOfPublication;
        this.copiesInStock = copiesInStock;
    }

    // Getters
    public String getPublisher() {
        return publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public String getCallMark() {
        return callMark;
    }

    public int getCopiesInStock() {
        return copiesInStock;
    }

    // ToString method for displaying book information
    @Override
    public String toString() {
        return "Book{" +
                "callMark=" + callMark +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", authorSurname='" + authorSurname + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", copiesInStock=" + copiesInStock +
                '}';
    }
}
