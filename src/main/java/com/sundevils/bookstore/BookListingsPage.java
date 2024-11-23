package com.sundevils.bookstore;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class BookListingsPage extends Page {
    public BookListingsPage() {
        title = "Book Listings";

        Button accountButton = new Button("Account");
		accountButton.setStyle("-fx-font-size: 20px; -fx-background-color: #8e0c3a; -fx-text-fill: gold;");
		
		HBox account = new HBox(accountButton);
		account.setPadding(new Insets(0, 50, 0, 50));
		account.setAlignment(Pos.TOP_RIGHT);
        
		TextField searchBar = new TextField();
		searchBar.setPromptText("Search...");
		searchBar.setStyle("-fx-font-size: 16px");
		searchBar.setMinWidth(1220);
		
		Button cartButton = new Button("Cart");
		cartButton.setStyle("-fx-font-size: 16px");
		
		HBox search = new HBox(searchBar, cartButton);
		search.setPadding(new Insets(10, 50, 0, 50));
		
		ComboBox<String> categoryComboBox = new ComboBox<>();
		categoryComboBox.getItems().addAll("Natural Science", "Math", "Fiction");
		categoryComboBox.setPromptText("Select Category");
		categoryComboBox.setStyle("-fx-font-size: 16px");
		
		ComboBox<String> conditionComboBox = new ComboBox<>();
		conditionComboBox.getItems().addAll("Used, Like New", "Moderately Used", "Heavily Used");
		conditionComboBox.setPromptText("Select Condition");
		conditionComboBox.setStyle("-fx-font-size: 16px");
				
		HBox bookOptions = new HBox(20, categoryComboBox, conditionComboBox);
		bookOptions.setPadding(new Insets(10, 50, 0, 50));
		
		GridPane bookGrid = new GridPane();
		bookGrid.setHgap(50);
		bookGrid.setVgap(20);
		bookGrid.setPadding(new Insets(20, 50, 50, 50));
		
		for (int i = 0; i < 48; i++) {
			VBox book = createBookObject();
			book.setMaxSize(100, 200);
			
			bookGrid.add(book, i % 8, i / 8);			
		}
				
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(bookGrid);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setPadding(new Insets(20, 50, 50, 50)); 
        scrollPane.setStyle("-fx-background: #191919; -fx-border-color: #191919;");
        
		VBox vbox = new VBox(account, search, bookOptions, scrollPane);
		vbox.setPadding(new Insets(70, 50, 0, 50));
		
		contentPane.getChildren().addAll(vbox);
        contentPane.setStyle("-fx-background-color: #191919;"); // TODO: remove
    }    
    public VBox createBookObject() {
    	VBox book = new VBox(5);
    	   	
    	Region bookCover = new Region();
        bookCover.setPrefSize(100, 140);
        bookCover.setStyle("-fx-background-color: gray; -fx-border-color: black; -fx-border-width: 2;");
        
        bookCover.setMaxSize(100, 150);
        
        VBox bookDetails = new VBox();
        bookDetails.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Book Title");
        titleLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label authorLabel = new Label("Author");
        authorLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label yearLabel = new Label("2024");
        yearLabel.setStyle("-fx-text-fill: #FFC425;");
		
		Label inventoryLabel = new Label("Listings: 5");
        inventoryLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label priceLabel = new Label("$15.00");
        priceLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #8e0c3a;");
                
        bookDetails.getChildren().addAll(titleLabel, authorLabel, yearLabel, inventoryLabel, priceLabel);
        
        VBox bookContent = new VBox(5);
        bookContent.setAlignment(Pos.CENTER);
        bookContent.getChildren().addAll(bookCover, bookDetails);
        
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-font-size: 12px; -fx-background-color: #8e0c3a; -fx-text-fill: white;");
        
        
        book.getChildren().addAll(bookContent, addToCartButton);
        book.setAlignment(Pos.CENTER);
            	
    	return book;
    }
    
}
