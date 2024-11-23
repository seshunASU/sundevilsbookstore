package com.sundevils.bookstore;

public class SellerView extends UserView {
    private Tab activeListingsTab;
    private Tab salesHistoryTab;

    public ActiveListingsPage activeListingsPage;
    public NewListingsPage newListingsPage;
    public SalesHistoryPage salesHistoryPage;

    public SellerView() {
        super();
        
        activeListingsTab = new Tab("Active Listings");
        salesHistoryTab = new Tab("Sales History");

        activeListingsPage = new ActiveListingsPage();
        newListingsPage = new NewListingsPage();
        salesHistoryPage = new SalesHistoryPage();

        activeListingsTab.associatePage(activeListingsPage);
        activeListingsTab.associatePage(newListingsPage);
        salesHistoryTab.associatePage(salesHistoryPage);
        
        tabPane.getTabs().addAll(activeListingsTab, salesHistoryTab);
        
        setPage(activeListingsPage);
    }

    public void resetState() {
        
    }
}
