package com.sundevils.bookstore;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Page {
    protected StackPane contentPane;
    protected String title;

    public Page() {
        contentPane = new StackPane();
        title = "";
    }

    public Pane getContentPane() {
        return contentPane;
    }
}
