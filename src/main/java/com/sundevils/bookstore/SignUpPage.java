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

public class SignUpPage extends Page {
    public SignUpPage() {
        title = "Sign up";
        backPage = "Login";

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(60, 80, 60, 80));
        grid.setStyle("-fx-background-color: #111111;");

        // Title Text
        Text titleText = new Text("Sign up");
        titleText.setFont(Font.font("", FontWeight.BOLD, 48));
        titleText.setFill(Color.DARKRED);

        StackPane titlePane = new StackPane(titleText);
        titlePane.setAlignment(Pos.CENTER);
        grid.add(titlePane, 0, 0, 2, 1);

        // Username Field
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        usernameLabel.setTextFill(Color.GOLD);
        grid.add(usernameLabel, 0, 1);
        TextField usernameField = new TextField();
        usernameField.setPrefSize(400, 50);
        grid.add(usernameField, 1, 1);

        // Password Field
        Label passwordLabel = new Label("Password");
        passwordLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        passwordLabel.setTextFill(Color.GOLD);
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(400, 50);
        grid.add(passwordField, 1, 2);

        // Confirm Password Field
        Label confirmPasswordLabel = new Label("Confirm Password");
        confirmPasswordLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        confirmPasswordLabel.setTextFill(Color.GOLD);
        grid.add(confirmPasswordLabel, 0, 3);
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPrefSize(400, 50);
        grid.add(confirmPasswordField, 1, 3);

        // Email Field
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        emailLabel.setTextFill(Color.GOLD);
        grid.add(emailLabel, 0, 4);
        TextField emailField = new TextField();
        emailField.setPrefSize(400, 50);
        grid.add(emailField, 1, 4);

        // Sign Up Button
        Button signUpBtn = new Button("Sign up");
        signUpBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
        signUpBtn.setPrefSize(300, 60);
        signUpBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        signUpBtn.setCursor(Cursor.HAND);
        grid.add(signUpBtn, 1, 5);

        // Error text
        Text errorText = new Text();
        errorText.setFill(Color.RED);
        grid.add(errorText, 1, 6);

        // Sign Up Button Action
        signUpBtn.setOnAction(e -> {
            String usernameInput = usernameField.getText();
            String passwordInput = passwordField.getText();
            String confirmPasswordInput = confirmPasswordField.getText();
            String emailInput = emailField.getText();

            if (usernameInput.isEmpty() || passwordInput.isEmpty() || confirmPasswordInput.isEmpty() || emailInput.isEmpty()) {
                errorText.setText("All fields are required.");
            } else if (!passwordInput.equals(confirmPasswordInput)) {
                errorText.setText("Passwords do not match.");
            } else {
                // Handle user registration logic here
                System.out.println("User registered successfully!");
                errorText.setText("");
            }
        });

        contentPane.getChildren().add(grid);
    }
}
