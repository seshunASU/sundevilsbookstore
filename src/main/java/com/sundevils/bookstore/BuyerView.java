package com.sundevils.bookstore;

public class BuyerView extends UserView {
    private Tab booksTab;

    public BookListingsPage bookListingsPage;
    public CartPage cartPage;

    public BuyerView() {
        super();
        
        booksTab = new Tab("Books");

        bookListingsPage = new BookListingsPage();
        cartPage = new CartPage();

        booksTab.associatePage(bookListingsPage);
        booksTab.associatePage(cartPage);

        tabPane.getTabs().addAll(booksTab);
        
        registerPage(bookListingsPage);
        registerPage(cartPage);
        
        setPage(bookListingsPage);
    }

    public void resetState() {
        
    }
}
