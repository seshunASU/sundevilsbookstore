package com.sundevils.bookstore;

import java.util.ArrayList;

public class Tab extends javafx.scene.control.Tab {
    public ArrayList<Page> pages;
    public Page activePage;

    public Tab(String title) {
        super(title);
        pages = new ArrayList<>();
    }

    public void associatePage(Page page) {
        if (activePage == null) {
            setPage(page);
        }

        pages.add(page);
        page.associateTab(this);
    }

    public void setPage(Page page) {
        activePage = page;
        setContent(page.getContentPane());
    }
}
