package com.sundevils.bookstore;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {
    private View activeView;
    private Stage stage;

    public LoginView loginView;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        loginView = new LoginView();
        
        setActiveView(loginView);
    }

    public void setActiveView(View view) {
        activeView = view;

        stage.setScene(view.getScene());
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
