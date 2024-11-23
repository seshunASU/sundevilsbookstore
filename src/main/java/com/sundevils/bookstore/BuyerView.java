package com.sundevils.bookstore;
import javafx.scene.control.Tab;

public class BuyerView extends UserView {
    private Tab booksTab;

    public BookListingsPage bookListingsPage;
    public CartPage cartPage;

    public BuyerView() {
        super();
        
        booksTab = new Tab("Books");

        bookListingsPage = new BookListingsPage();
        cartPage = new CartPage();

        bookListingsPage.associateTab(booksTab);
        cartPage.associateTab(booksTab);
        
        tabPane.getTabs().addAll(booksTab);
        
        setPage(bookListingsPage);
    }
}
