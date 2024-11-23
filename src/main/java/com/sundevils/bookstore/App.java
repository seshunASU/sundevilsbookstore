package com.sundevils.bookstore;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.HashMap;
import javafx.util.Pair;

public class App extends Application  {
    private static App instance;

    public User loggedInUser;
    public boolean loaded;

    private View activeView;
    private Stage stage;
    private Scene scene;
    private Database db;
    
    public UserDB userDB;
    public BookDB bookDB;
    public ListingDB listingDB;

    public LoginView loginView;
    public BuyerView buyerView;
    public SellerView sellerView;
    public AdminView adminView;

    private static HashMap<String, Pair<Page, View>> pageRegistry = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        // Set singleton
        instance = this;

        // Databases
        db = new Database();
        userDB = new UserDB(db);
        bookDB = new BookDB(db);
        listingDB = new ListingDB(db);

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
        stage.setMaximized(true);
        stage.show();

        // Finalizing
        loaded = true;
        App.updateWindowTitle();
    }

    public void resetState() {
        loginView.resetState();
        buyerView.resetState();
        sellerView.resetState();
        adminView.resetState();
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

    // Singleton methods
    public static App getInstance() {
        return instance;
    }

    public static User getLoggedInUser() {
        return App.getInstance().loggedInUser;
    }

    public static Buyer getLoggedInBuyer() {
        return App.getLoggedInUser().convertToBuyer();
    }

    public static Seller getLoggedInSeller() {
        return App.getLoggedInUser().convertToSeller();
    }
    
    public static void registerPage(Page page, View view) {
        pageRegistry.put(page.getTitle(), new Pair<>(page, view));
    }

    public static Pair<Page, View> getPageAndView(String pageTitle) {
        return pageRegistry.get(pageTitle);
    }
    
    public static void updateWindowTitle() {
        App app = App.getInstance();

        if (app.loaded && app != null) {
            app.setTitle("Sundevils Bookstore - " + app.getActiveView().getActivePageTitle());
        }
    }

    public static UserDB getUserDB() {
        return getInstance().userDB;
    }

    // Main method
    public static void main(String[] args) {
        launch(args);
    }
}
