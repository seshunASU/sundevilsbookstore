package com.sundevils.bookstore;
import javafx.scene.control.Tab;

public class SellerView extends UserView {
    private Tab activeListingsTab;
    private Tab salesHistoryTab;

    public ActiveListingsPage activeListingsPage;
    public NewListingsPage newListingsPage;

    public SellerView() {
        super();
        
        activeListingsTab = new Tab("Active Listings");
        salesHistoryTab = new Tab("Sales History");

        activeListingsPage = new ActiveListingsPage();
        newListingsPage = new NewListingsPage();

        activeListingsPage.associateTab(activeListingsTab);
        newListingsPage.associateTab(activeListingsTab);
        
        tabPane.getTabs().addAll(activeListingsTab, salesHistoryTab);
        
        setPage(activeListingsPage);
    }
}
