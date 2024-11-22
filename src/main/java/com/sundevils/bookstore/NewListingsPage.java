import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
// TO-DO: BACK BUTTON

public class NewListingsPage extends Page {

	public NewListingsPage() {
		
		title = "Create New Listing";
        
        // Creating the grid layout for the login page
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(60, 80, 60, 80));
        grid.setStyle("-fx-background-color: #111111;");
        
        Text welcomeText = new Text("Create New Listing");
        welcomeText.setFont(Font.font("", FontWeight.BOLD, 26));
        welcomeText.setFill(Color.GOLD);
        grid.add(welcomeText, 0, 0, 2, 1);
        GridPane.setHalignment(welcomeText, HPos.CENTER);
        
        Label book = new Label("Book");
		TextField bookField = new TextField();
		book.setFont(Font.font("", FontWeight.BOLD, 16));
        book.setTextFill(Color.GOLD);
		
		Label author = new Label("Author");
		TextField authorField = new TextField();
		author.setFont(Font.font("", FontWeight.BOLD, 16));
        author.setTextFill(Color.GOLD);
		
		ObservableList<String> bookCategories = FXCollections.observableArrayList(
                Arrays.asList("Natural Science", "Math", "Fiction")
        );
		
		Label category = new Label("Category");
		ComboBox<String> categoryComboBox = new ComboBox<>(bookCategories);
		category.setFont(Font.font("", FontWeight.BOLD, 16));
        category.setTextFill(Color.GOLD);
		
		Label year = new Label("Year");
		TextField yearField = new TextField();
		year.setFont(Font.font("", FontWeight.BOLD, 16));
        year.setTextFill(Color.GOLD);
		
		
		Label origPrice = new Label("Original Price");
		TextField origPriceField = new TextField();
		origPrice.setFont(Font.font("", FontWeight.BOLD, 16));
        origPrice.setTextFill(Color.GOLD);
		
		// TO-DO redefine conditions	
		Label condition = new Label("Condition");
		ObservableList<String> conditionCategories = FXCollections.observableArrayList(
                Arrays.asList("Like New", "Moderately Used", "Heavily Used")
        );
		ComboBox<String> conditionComboBox = new ComboBox<>(conditionCategories);
		condition.setFont(Font.font("", FontWeight.BOLD, 16));
        condition.setTextFill(Color.GOLD);
        
		// TO-DO how many quantity categories?
		Label quantity = new Label("Quantity");
		Spinner<Integer> quantitySpinner = new Spinner<Integer>();		
		SpinnerValueFactory<Integer> quantityValues = 
				new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99, 1);
        quantitySpinner.setValueFactory(quantityValues);
		quantity.setFont(Font.font("", FontWeight.BOLD, 16));
        quantity.setTextFill(Color.GOLD);
		
		int preferredWidth = 150;
		int preferredHeight = 25;
		
		bookField.setPrefSize(preferredWidth, preferredHeight);
		authorField.setPrefSize(preferredWidth, preferredHeight);
		categoryComboBox.setPrefSize(preferredWidth, preferredHeight);
		yearField.setPrefSize(preferredWidth, preferredHeight);
		origPriceField.setPrefSize(preferredWidth, preferredHeight);
		conditionComboBox.setPrefSize(preferredWidth, preferredHeight);
		quantitySpinner.setPrefSize(preferredWidth, preferredHeight);
		
		grid.add(book, 0, 1);
		grid.add(bookField, 1, 1);
		grid.add(author, 0, 2);
		grid.add(authorField, 1, 2);
		grid.add(category, 0, 3);
		grid.add(categoryComboBox, 1, 3);
		grid.add(year, 0, 4);
		grid.add(yearField, 1, 4);
		grid.add(origPrice, 0, 5);
		grid.add(origPriceField, 1, 5);
		grid.add(condition, 0, 6);
		grid.add(conditionComboBox, 1, 6);
		grid.add(quantity, 0, 7);
		grid.add(quantitySpinner, 1, 7);
		
		Text listingPrice = new Text("Listing Price");
		listingPrice.setFont(Font.font("", FontWeight.BOLD, 32));
        listingPrice.setFill(Color.GOLD);
        HBox listingPriceH = new HBox(listingPrice);
        listingPriceH.setAlignment(Pos.CENTER);
        listingPriceH.setPadding(Insets.EMPTY);
        grid.add(listingPriceH, 0, 8, 2, 1);
        
        Text dollarSign = new Text("$");
        dollarSign.setFont(Font.font("", FontWeight.BOLD, 42));
        dollarSign.setFill(Color.web("#8e0c3a"));
		
        Text priceText = new Text("20.00");
        priceText.setFont(Font.font("", FontWeight.BOLD, 42));
        priceText.setFill(Color.GOLD);
        
        HBox priceH = new HBox(0, dollarSign, priceText);
        priceH.setAlignment(Pos.CENTER);
        priceH.setPadding(Insets.EMPTY);
        
        grid.add(priceH, 0, 9, 2, 1);
        
        Button listButton = new Button("List!");
        listButton.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: BOLD;"
        		+ "-fx-background-color: #8e0c3a"
        		+ "; -fx-text-fill: gold");
        listButton.setPrefSize(85, 35);
        HBox listButtonH = new HBox(listButton);
        listButtonH.setAlignment(Pos.CENTER);
        
		grid.add(listButtonH, 0, 10, 2, 1);
        
		VBox vbox = new VBox(20);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(grid);
		
		contentPane.getChildren().add(grid);
        contentPane.setStyle("-fx-background-color: rgba(255, 0, 0, 0.3);"); // TODO: remove
        
	}
}
