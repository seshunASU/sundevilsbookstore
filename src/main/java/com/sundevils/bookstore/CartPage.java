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

public class CartPage extends Page {
    private Label subtotalLabel;
    private Label taxLabel;
    private Label totalLabel;
    private VBox cartItemsContainer;

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

        payButton.setOnAction(e -> {
            ArrayList<Listing> cart = App.getLoggedInBuyer().cart;
            for (Listing cartListing : cart) {
                cartListing.updateStatus(ListingStatus.UNAVAILABLE);
            }
            App.getLoggedInBuyer().cart.clear();

            BuyerView buyerView = App.getInstance().buyerView;
            buyerView.bookListingsPage.updateBookListings();
            
            buyerView.setPage(buyerView.bookListingsPage);
        });

        contentPane.getChildren().add(root);
    }

    public void updateCart() {
        System.out.println("updating cart");

        ArrayList<Listing> cart = App.getLoggedInBuyer().cart;
        cartItemsContainer.getChildren().clear();

        // populate ui elements
        boolean firstItem = true;
        double subtotal = 0;

        for (Listing cartListing : cart) {
            System.out.println("found cart item");
            CartUIItem cartUIItem = new CartUIItem(cartListing);

            if (!firstItem) {
                Separator separator = new Separator();
                separator.setStyle("-fx-background-color: gold; -fx-pref-height: 2px;");
                cartItemsContainer.getChildren().add(separator);
            }
            
            cartItemsContainer.getChildren().add(cartUIItem);
            subtotal += cartListing.getPrice();

            firstItem = false;
        }

        // update totals 
        double tax = subtotal * 0.07;
        double total = subtotal + tax;
        subtotalLabel.setText(String.format("Subtotal: $%.2f", subtotal));
        taxLabel.setText(String.format("Sales Tax: $%.2f", tax));
        totalLabel.setText(String.format("Total: $%.2f", total));
    }

    private class CartUIItem extends HBox {
        private Spinner<Integer> quantitySpinner;
        private ComboBox<String> conditionComboBox;
        private Label priceLabel;

        public CartUIItem(Listing cartListing) {
            super(20);

            Book listingBook = cartListing.getBook();
            String title = listingBook.getTitle();
            String author = listingBook.getAuthor();
            double basePrice = cartListing.getPrice();

            setAlignment(Pos.CENTER_LEFT);
            setPadding(new Insets(10));
            setStyle("-fx-background-color: #111111;");

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
            // quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> updateTotals());

            Label conditionLabel = new Label("Condition");

            conditionComboBox = new ComboBox<>();
            conditionComboBox.getItems().addAll("New", "Used, Like New", "Used, Good");
            conditionComboBox.setValue("New");
            // conditionComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updatePrice());
            controls.getChildren().addAll(quantityLabel, quantitySpinner, conditionLabel, conditionComboBox);

            priceLabel = new Label();
            priceLabel.setFont(Font.font("", FontWeight.BOLD, 18));
            priceLabel.setText(String.format("$%.2f", basePrice));

            Button removeButton = new Button("Remove");
            removeButton.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
            removeButton.setCursor(Cursor.HAND);

            removeButton.setOnAction(e -> {
                ArrayList<Listing> cart = App.getLoggedInBuyer().cart;
                cart.remove(cartListing);
                updateCart();
            });

            getChildren().addAll(bookCover, details, controls, removeButton, priceLabel);
        }
    }
}