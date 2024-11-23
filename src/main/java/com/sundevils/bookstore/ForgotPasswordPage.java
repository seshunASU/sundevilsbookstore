package com.sundevils.bookstore;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ForgotPasswordPage extends Page {
    public ForgotPasswordPage() {
        title = "Forgot Password";
        backPage = "Login";

        // Create the grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(20);
        grid.setHgap(20);
        grid.setPadding(new Insets(60, 80, 60, 80));
        grid.setStyle("-fx-background-color: #111111;");

        // Title
        Text titleText = new Text("Forgot Password");
        titleText.setFont(Font.font("", FontWeight.BOLD, 48));
        titleText.setFill(Color.DARKRED);

        StackPane titlePane = new StackPane(titleText);
        titlePane.setAlignment(Pos.CENTER);
        grid.add(titlePane, 0, 0, 2, 1); // Spanning 2 columns

        // Email Field
        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        emailLabel.setTextFill(Color.GOLD);
        grid.add(emailLabel, 0, 1);

        TextField emailField = new TextField();
        emailField.setPrefSize(400, 50);
        grid.add(emailField, 1, 1);

        // Username Field
        Label usernameLabel = new Label("Username");
        usernameLabel.setFont(Font.font("", FontWeight.BOLD, 20));
        usernameLabel.setTextFill(Color.GOLD);
        grid.add(usernameLabel, 0, 2);

        TextField usernameField = new TextField();
        usernameField.setPrefSize(400, 50);
        grid.add(usernameField, 1, 2);

        // Send Email Button
        Button sendEmailBtn = new Button("Send Email");
        sendEmailBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: yellow;");
        sendEmailBtn.setPrefSize(300, 60);
        sendEmailBtn.setFont(Font.font("", FontWeight.BOLD, 20));
        sendEmailBtn.setCursor(Cursor.HAND);
        grid.add(sendEmailBtn, 1, 3); // Centered under inputs

        // Add button action
        sendEmailBtn.setOnAction(e -> {
            String emailInput = emailField.getText();
            String usernameInput = usernameField.getText();

            if (emailInput.isEmpty() || usernameInput.isEmpty()) {
                System.out.println("Please fill out all fields.");
            } else {
                System.out.println("Reset email sent to: " + emailInput);
            }
        });

        contentPane.getChildren().add(grid);
    }
}
