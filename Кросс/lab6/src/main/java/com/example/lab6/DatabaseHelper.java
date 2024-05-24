package com.example.lab6;

import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    public static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book(
                        rs.getString("callMark"),
                        rs.getString("publisher"),
                        rs.getString("title"),
                        rs.getString("authorSurname"),
                        rs.getInt("yearOfPublication"),
                        rs.getInt("copiesInStock")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static List<Book> searchBooks(String searchQuery) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(authorSurname) LIKE ? OR LOWER(title) LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchQuery.toLowerCase() + "%");
            pstmt.setString(2, "%" + searchQuery.toLowerCase() + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("callMark"),
                            rs.getString("publisher"),
                            rs.getString("title"),
                            rs.getString("authorSurname"),
                            rs.getInt("yearOfPublication"),
                            rs.getInt("copiesInStock")
                    );
                    books.add(book);
                    System.out.println(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static List<Book> getBooksByPublisher(String publisher) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE LOWER(publisher) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(2, publisher.toLowerCase());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Book book = new Book(
                            rs.getString("callMark"),
                            rs.getString("publisher"),
                            rs.getString("title"),
                            rs.getString("authorSurname"),
                            rs.getInt("yearOfPublication"),
                            rs.getInt("copiesInStock")
                    );
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public static void addBook(TextField callMark,TextField publisher, TextField title, TextField authorSurname, TextField yearOfPublication, TextField copiesInStock) {
        String sql = "INSERT INTO books (callMark,publisher, title, authorSurname, yearOfPublication, copiesInStock) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, callMark.getText());
            pstmt.setString(2, publisher.getText());
            pstmt.setString(3, title.getText());
            pstmt.setString(4, authorSurname.getText());
            pstmt.setInt(5, Integer.parseInt(yearOfPublication.getText()));
            pstmt.setInt(6, Integer.parseInt(copiesInStock.getText()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding book to the database: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

}
