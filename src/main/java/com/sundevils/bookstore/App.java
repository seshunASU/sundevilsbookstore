package com.sundevils.bookstore;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class App extends Application  {
    private static App instance;

    private View activeView;
    private Stage stage;
    private Scene scene;
    private Database db;
    
    public UserDB userDB;

    public LoginView loginView;
    public BuyerView buyerView;
    public SellerView sellerView;
    public AdminView adminView;

    @Override
    public void start(Stage primaryStage) {
        // Databases
        db = new Database();
        userDB = new UserDB(db);

        // UI
        stage = primaryStage;
        
        loginView = new LoginView();
        buyerView = new BuyerView();
        sellerView = new SellerView();
        adminView = new AdminView();

        // Finalize UI
        activeView = loginView;
        scene = new Scene(activeView.getContentPane());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);

        stage.setMinWidth(1000);
        stage.setMinHeight(720);
        stage.show();

        // Set singleton
        instance = this;

        // Finalizing
        App.updateWindowTitle();
    }

    public View getActiveView() {
        return activeView;
    }
    
    public void setActiveView(View view) {
        activeView = view;
        scene.setRoot(view.getContentPane());
        App.updateWindowTitle();
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public static void updateWindowTitle() {
        App app = App.getInstance();

        if (app != null) {
            app.setTitle("Sundevils Bookstore - " + app.getActiveView().getActivePageTitle());
        }
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
