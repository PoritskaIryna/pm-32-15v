package com.example.lab6;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class BookApp<Book> extends Application {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookstore Management System");
        VBox layout = new VBox();
        layout.setSpacing(5);
        TextField callMarkInput = new TextField();
        callMarkInput.setPromptText("CallMark");
        TextField publisherInput = new TextField();
        publisherInput.setPromptText("Publisher");
        TextField titleInput = new TextField();
        titleInput.setPromptText("Title");
        TextField authorSurnameInput = new TextField();
        authorSurnameInput.setPromptText("Author Surname");
        TextField yearOfPublicationInput = new TextField();
        yearOfPublicationInput.setPromptText("Year of Publication");
        TextField copiesInStockInput = new TextField();
        copiesInStockInput.setPromptText("Copies in Stock");
        Button addButton = new Button("Add Book");
        addButton.setOnAction(e -> {
            DatabaseHelper.addBook(callMarkInput,publisherInput, titleInput, authorSurnameInput, yearOfPublicationInput, copiesInStockInput);
            loadBooksFromDatabase();
        });
        // Search fields
        TextField searchInput = new TextField();
        searchInput.setPromptText("Search by author surname or title");
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchBooks(searchInput.getText()));

        TextField publisherSearchInput = new TextField();
        publisherSearchInput.setPromptText("Search by publisher");
        Button publisherSearchButton = new Button("Search Publisher");
        publisherSearchButton.setOnAction(e -> searchBooksByPublisher(publisherSearchInput.getText()));

        loadBooksFromDatabase(); // Initial load of books

        // Table setup
        TableView<Book> table = setupTableView();

        layout.getChildren().addAll(
                new Label("Add New Book:"),
                callMarkInput,publisherInput, titleInput, authorSurnameInput, yearOfPublicationInput, copiesInStockInput, addButton,
                new Label("Process File:"),
                new Label("Search:"), searchInput, searchButton,
                new Label("Search by Publisher:"), publisherSearchInput, publisherSearchButton,
                table);

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private TableView<Book> setupTableView() {
        TableView<Book> table = new TableView<>();
        table.setItems(books);
        String[] columnMap = {"callMark","publisher", "title", "authorSurname", "yearOfPublication", "copiesInStock"};
        String[] columnNames = {"Call mark","Publisher", "Title", "Author Surname", "Year of Publication", "Copies in Stock"};
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn<Book, String> column = new TableColumn<>(columnNames[i]);
            column.setCellValueFactory(new PropertyValueFactory<>(columnMap[i]));
            table.getColumns().add(column);
        }
        return table;
    }


    private void searchBooks(String query) {
        List<Book> booksFromDb = (List<Book>) DatabaseHelper.searchBooks(query);
        books.clear();
        books.addAll(booksFromDb);
    }

    private void searchBooksByPublisher(String publisher) {
        List<Book> booksFromDb = (List<Book>) DatabaseHelper.getBooksByPublisher(publisher);
        books.clear();
        books.addAll(booksFromDb);
    }

    private void loadBooksFromDatabase() {
        List<Book> booksFromDb = (List<Book>) DatabaseHelper.getBooks();
        books.clear();
        books.addAll(booksFromDb);
    }
}
