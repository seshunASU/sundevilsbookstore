package com.sundevils.bookstore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BookListingsPage extends Page {
    public BookListingsPage() {
        title = "Book Listings";
        
        // TODO: clear copy & pasted code and actually make the page

        // Creating the grid layout for the login page
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(60, 80, 60, 80));
        grid.setStyle("-fx-background-color: #111111;");

        // Welcome Text
        Text welcomeText = new Text("Placeholder Books Listings Page");
        welcomeText.setFont(Font.font("", FontWeight.BOLD, 48));
        welcomeText.setFill(Color.DARKRED);

        StackPane welcomePane = new StackPane(welcomeText);
        welcomePane.setAlignment(Pos.CENTER);
        grid.add(welcomePane, 0, 0, 2, 1);

        // Navigation Test Button
        Button testBtn = new Button("Cart");
        testBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
        testBtn.setPrefSize(300, 60);
        testBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        testBtn.setCursor(Cursor.HAND);
        grid.add(testBtn, 1, 5);

        contentPane.getChildren().add(grid);

        // Test code
        testBtn.setOnAction(e -> {
            BuyerView buyerView = App.getInstance().buyerView;
            buyerView.setPage(buyerView.cartPage);
        });
    }
}