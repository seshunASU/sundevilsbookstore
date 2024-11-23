package com.sundevils.bookstore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends Page {
    private Label subtotalLabel;
    private Label taxLabel;
    private Label totalLabel;
    private VBox cartItemsContainer;
    private List<CartItem2> cartItems = new ArrayList<>();

    public CartPage() {
        title = "Shopping Cart";
        backPage = "Book Listings";

        subtotalLabel = new Label("Subtotal: $0.00");
        taxLabel = new Label("Sales Tax: $0.00");
        totalLabel = new Label("Total: $0.00");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #111111;");

        Label title = new Label("Shopping Cart");
        title.setFont(Font.font("", FontWeight.BOLD, 30));
        title.setTextFill(Color.GOLD);
        title.setPadding(new Insets(20));
        
        StackPane titlePane = new StackPane(title);
        titlePane.setAlignment(Pos.CENTER);
        root.setTop(titlePane);

        cartItemsContainer = new VBox(10);
        cartItemsContainer.setPadding(new Insets(20));
        cartItemsContainer.setStyle("-fx-background-color: #111111;");

        for (int i = 0; i < CartManager.getBookCartNumber(); i++) {
            CartItem cartItem = new CartItem("Book Title " + (i + 1), "Author " + (i + 1), 15.0);
            cartItems.add(cartItem);
            cartItemsContainer.getChildren().add(cartItem.createBookItem());
            if (i < CartManager.getBookCartNumber() - 1) {
                Separator separator = cartItem.createSeparator();
                cartItemsContainer.getChildren().add(separator);
            }
        }

        ScrollPane scrollPane = new ScrollPane(cartItemsContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #111111; -fx-border-color: #111111;");
        root.setCenter(scrollPane);

        VBox checkoutArea = new VBox(20);
        checkoutArea.setPadding(new Insets(20));
        checkoutArea.setAlignment(Pos.CENTER);
        subtotalLabel.setTextFill(Color.GOLD);
        subtotalLabel.setFont(Font.font("", FontWeight.BOLD, 18));
        taxLabel.setTextFill(Color.GOLD);
        taxLabel.setFont(Font.font("", FontWeight.BOLD, 18));
        totalLabel.setTextFill(Color.GOLD);
        totalLabel.setFont(Font.font("", FontWeight.BOLD, 24));

        Button payButton = new Button("Pay");
        payButton.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
        payButton.setFont(Font.font("", FontWeight.BOLD, 20));
        payButton.setCursor(Cursor.HAND);
        payButton.setPrefSize(200, 50);
        checkoutArea.getChildren().addAll(subtotalLabel, taxLabel, totalLabel, payButton);
        root.setRight(checkoutArea);

        updateTotals();

        contentPane.getChildren().add(root);
    }
    public class CartManager {
        private static int bookCartNumber = 0;
        public static int getBookCartNumber() {
            return bookCartNumber;
        }
        public static void incrementBookCartNumber() {
            bookCartNumber++;
        }
    }

    private void updateTotals() {
        double subtotal = 0;
        for (CartItem2 item : cartItems) {
            subtotal += item.calculateSubtotal();
        }
        double tax = subtotal * 0.07;
        double total = subtotal + tax;
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        taxLabel.setText(String.format("Sales Tax: $%.2f", tax));
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private class CartItem2 {
        private Spinner<Integer> quantitySpinner;
        private ComboBox<String> conditionComboBox;
        private Label priceLabel;
        private HBox itemBox;
        private Separator separator;
        private String title;
        private String author;
        private double basePrice;

        public CartItem2(String title, String author, double basePrice) {
            //IMPORT book details from book database !!!IMPORTANT!!!
            this.title = title;
            this.author = author;
            this.basePrice = basePrice;
        }

        public HBox createBookItem() {
            itemBox = new HBox(20);
            itemBox.setAlignment(Pos.CENTER_LEFT);
            itemBox.setPadding(new Insets(10));
            itemBox.setStyle("-fx-background-color: #111111;");

            Region bookCover = new Region();
            bookCover.setPrefSize(60, 80);
            bookCover.setStyle("-fx-background-color: gray; -fx-border-color: black;");
            VBox details = new VBox(5);
            details.setAlignment(Pos.TOP_LEFT);

            Label titleLabel = new Label(title);
            titleLabel.setFont(Font.font("", FontWeight.BOLD, 16));
            Label authorLabel = new Label("by " + author);
            authorLabel.setFont(Font.font("", FontWeight.NORMAL, 14));
            details.getChildren().addAll(titleLabel, authorLabel);

            VBox controls = new VBox(10);
            controls.setAlignment(Pos.CENTER);
            Label quantityLabel = new Label("Quantity");
            quantitySpinner = new Spinner<>(1, 10, 1);
            quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateTotals());
            Label conditionLabel = new Label("Condition");
            conditionComboBox = new ComboBox<>();
            conditionComboBox.getItems().addAll("New", "Used, Like New", "Used, Good");
            conditionComboBox.setValue("New");
            conditionComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updatePrice());
            controls.getChildren().addAll(quantityLabel, quantitySpinner, conditionLabel, conditionComboBox);

            priceLabel = new Label();
            priceLabel.setFont(Font.font("", FontWeight.BOLD, 18));
            updatePrice();

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
            removeButton.setCursor(Cursor.HAND);
            removeButton.setOnAction(e -> {
                cartItems.remove(this);
                cartItemsContainer.getChildren().removeAll(itemBox, separator);
                updateTotals();
            });
            itemBox.getChildren().addAll(bookCover, details, controls, removeButton, priceLabel);
            return itemBox;
        }

        public Separator createSeparator() {
            separator = new Separator();
            separator.setStyle("-fx-background-color: gold; -fx-pref-height: 2px;");
            return separator;
        }

        private void updatePrice() {
            String condition = conditionComboBox.getValue();
            double pricePerUnit;
            //CHANGE price formula based on book price (ie. new = basePrice, used = basePrice * .9) !!Important!!
            //For price on the book in cart
            switch (condition) {
                case "New":
                    pricePerUnit = 15.0;
                    break;
                case "Used, Like New":
                    pricePerUnit = 13.0;
                    break;
                case "Used, Good":
                default:
                    pricePerUnit = 11.0;
                    break;
            }
            priceLabel.setText(String.format("$%.2f", pricePerUnit));
            updateTotals();
        }

        public double calculateSubtotal() {
            int quantity = quantitySpinner.getValue();
            String condition = conditionComboBox.getValue();
            double pricePerUnit;
            //CHANGE PRICE formula
            //For calculating subtotal, tax, and total
            switch (condition) {
                case "New":
                    pricePerUnit = 15.0;
                    break;
                case "Used, Like New":
                    pricePerUnit = 13.0;
                    break;
                case "Used, Good":
                default:
                    pricePerUnit = 11.0;
                    break;
            }
            return quantity * pricePerUnit;
        }
    }
}
