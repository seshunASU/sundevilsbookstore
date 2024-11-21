package com.sundevils.bookstore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        Text welcomeText = new Text("Welcome back!");
        welcomeText.setFont(Font.font("", FontWeight.BOLD, 48));
        welcomeText.setFill(Color.DARKRED);

        StackPane welcomePane = new StackPane(welcomeText);
        welcomePane.setAlignment(Pos.CENTER);
        grid.add(welcomePane, 0, 0, 2, 1);

        Text subText = new Text("Enter your credentials");
        subText.setFont(Font.font("", FontWeight.NORMAL, 24));
        subText.setFill(Color.GOLD);

        StackPane subTextPane = new StackPane(subText);
        subTextPane.setAlignment(Pos.CENTER);
        grid.add(subTextPane, 0, 1, 2, 1);

        GridPane.setMargin(subTextPane, new Insets(0, 0, 80, 0));

        // Username Field
        Label userLabel = new Label("Username");
        userLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        userLabel.setTextFill(Color.GOLD);
        grid.add(userLabel, 0, 2);

        TextField userTextField = new TextField();
        userTextField.setPrefSize(400, 50);
        grid.add(userTextField, 1, 2);

        // Password Field
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        passwordLabel.setTextFill(Color.GOLD);
        grid.add(passwordLabel, 0, 3);
        
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(400, 50);
        grid.add(passwordField, 1, 3);

        // Forgot Password
        Button forgotPasswordBtn = new Button("Forgot password?");
        forgotPasswordBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: darkred;");
        forgotPasswordBtn.setFont(Font.font("", FontWeight.NORMAL, 16));
        forgotPasswordBtn.setCursor(Cursor.HAND);
        grid.add(forgotPasswordBtn, 1, 4);
        GridPane.setMargin(forgotPasswordBtn, new Insets(0, 0, 10, 0));

        // Sign-In Button
        Button signInBtn = new Button("Sign In");
        signInBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
        signInBtn.setPrefSize(300, 60);
        signInBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        signInBtn.setCursor(Cursor.HAND);
        grid.add(signInBtn, 1, 5);

        // Sign-Up Button
        Button signUpBtn = new Button("Sign up");
        signUpBtn.setStyle("-fx-background-color: yellow; -fx-text-fill: darkred;");
        signUpBtn.setPrefSize(300, 60);
        signUpBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        signUpBtn.setCursor(Cursor.HAND);
        grid.add(signUpBtn, 1, 6);

        // Error text
        Text errorText = new Text();
        errorText.setFill(Color.RED);
        grid.add(errorText, 1, 7);

        contentPane.getChildren().add(grid);
        contentPane.setStyle("-fx-background-color: rgba(255, 0, 0, 0.3);"); // TODO: remove
    }
}