package com.sundevils.bookstore;
import javafx.scene.control.Tab;

public class BuyerView extends UserView {
    private Tab booksTab;

    public BookListingsPage bookListingsPage;

    public BuyerView() {
        super();
        
        booksTab = new Tab("Books");

        bookListingsPage = new BookListingsPage();

        bookListingsPage.associateTab(booksTab);
        
        tabPane.getTabs().addAll(booksTab);
        
        setPage(bookListingsPage);
    }
}
