package com.sundevils.bookstore;

public class BuyerView extends UserView {
    private BookListingsPage bookListingsPage;

    public BuyerView() {
        super();
        
        bookListingsPage = new BookListingsPage();

        setPage(bookListingsPage);
    }
}
