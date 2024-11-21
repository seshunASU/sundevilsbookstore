package com.sundevils.bookstore;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {
    private static App instance;

    private View activeView;
    private Stage stage;
    private Database db;
    
    public UserDB userDB;

    public LoginView loginView;
    public BuyerView buyerView;

    @Override
    public void start(Stage primaryStage) {
        // Databases
        db = new Database();
        userDB = new UserDB(db);

        // UI
        stage = primaryStage;
        
        loginView = new LoginView();
        buyerView = new BuyerView();

        setActiveView(buyerView);

        // Set singleton
        instance = this;
    }

    public void setActiveView(View view) {
        activeView = view;

        stage.setScene(view.getScene());
        stage.setMaximized(true);
        stage.show();
    }

    // Singleton methods
    public static App getInstance() {
        return instance;
    }

    public static UserDB getUserDB() {
        return getInstance().userDB;
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
