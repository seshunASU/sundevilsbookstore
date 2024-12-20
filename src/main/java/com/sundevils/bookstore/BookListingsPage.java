package com.sundevils.bookstore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Priority;

public class BookListingsPage extends Page {
	private GridPane bookGrid;

    public BookListingsPage() {
        title = "Book Listings";
        showSignoutButton = true;
          
		TextField searchBar = new TextField();
		searchBar.setPromptText("Search...");
		searchBar.setStyle("-fx-font-size: 16px");
		searchBar.setMinWidth(1220);
		
		Button cartButton = new Button("Cart");
		cartButton.setStyle("-fx-font-size: 16px");
		cartButton.setCursor(Cursor.HAND);
		cartButton.setOnAction(e -> {
            BuyerView buyerView = App.getInstance().buyerView;
			buyerView.cartPage.updateCart();
            buyerView.setPage(buyerView.cartPage);
        });
		
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
		
		bookGrid = new GridPane();
		bookGrid.setHgap(50);
		bookGrid.setVgap(20);
		bookGrid.setPadding(new Insets(20, 50, 50, 50));
		
		for (int i = 0; i < 7; i++) {
            		ColumnConstraints column = new ColumnConstraints();
	            	column.setMinWidth(120);
	            	column.setMaxWidth(120);
	           	 column.setHgrow(Priority.ALWAYS);
	            	bookGrid.getColumnConstraints().add(column);
		}
		
		
		for (int i = 0; i < 7; i++) {
			RowConstraints row = new RowConstraints();
			row.setMinHeight(275);
			row.setMaxHeight(275);
			row.setVgrow(Priority.ALWAYS);
			bookGrid.getRowConstraints().add(row);
		}
		
		updateBookListings();

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(bookGrid);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scrollPane.setPadding(new Insets(20, 50, 50, 50)); 
        scrollPane.setStyle("-fx-background: #111111; -fx-border-color: #111111;");
        
		VBox vbox = new VBox(search, bookOptions, scrollPane);
		vbox.setPadding(new Insets(70, 50, 0, 50));
		
		contentPane.getChildren().addAll(vbox);
    }    

	public void updateBookListings() {
		bookGrid.getChildren().clear();
		
		int index = 0;
		for (Listing listing : App.getInstance().listingDB.getListings()) {
			if (listing.getStatus() == ListingStatus.AVAILABLE) {
				VBox bookItem = createBookItem(listing);
                bookItem.setMaxSize(120, 275);
				int col = index % 7;
				int row = index / 7;

				bookGrid.add(bookItem, col, row);
				index++;
			}
		}
	}

	public VBox createBookItem(Listing listing) {
    	VBox book = new VBox(5);
    	   	
    	Region bookCover = new Region();
        bookCover.setPrefSize(100, 140);
        bookCover.setStyle("-fx-background-color: gray; -fx-border-color: black; -fx-border-width: 2;");
        
        bookCover.setMaxSize(100, 150);
        
        VBox bookDetails = new VBox();
        bookDetails.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(listing.getBook().getTitle());
        titleLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label authorLabel = new Label(listing.getBook().getAuthor());
        authorLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label yearLabel = new Label(String.valueOf(listing.getBook().getYear()));
        yearLabel.setStyle("-fx-text-fill: #FFC425;");
		
		Label inventoryLabel = new Label("Listings: 1"); // TODO: do
        inventoryLabel.setStyle("-fx-text-fill: #FFC425;");
        
		Label priceLabel = new Label("$" + listing.getPrice());
        priceLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: #8e0c3a;");
                
        bookDetails.getChildren().addAll(titleLabel, authorLabel, yearLabel, inventoryLabel, priceLabel);
        
        VBox bookContent = new VBox(5);
        bookContent.setAlignment(Pos.CENTER);
        bookContent.getChildren().addAll(bookCover, bookDetails);
        
        Button addToCartButton = new Button("Add to Cart");
        addToCartButton.setStyle("-fx-font-size: 12px; -fx-background-color: #8e0c3a; -fx-text-fill: white;");
        addToCartButton.setCursor(Cursor.HAND);
		addToCartButton.setOnAction(event -> {
			Buyer buyer = App.getLoggedInBuyer();
			buyer.cart.add(listing);
			addToCartButton.setDisable(true);
		});
        
        
        book.getChildren().addAll(bookContent, addToCartButton);
        book.setAlignment(Pos.CENTER);
            	
    	return book;
    }
}

