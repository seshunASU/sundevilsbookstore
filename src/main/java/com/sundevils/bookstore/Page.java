package com.sundevils.bookstore;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Page {
    protected StackPane contentPane;
    protected String title;
    protected Tab tab;

    public boolean showSignoutButton;
    public String backPage;

    public Page() {
        contentPane = new StackPane();
        title = "";
    }

    public String getTitle() {
        return title;
    }
    
    public Pane getContentPane() {
        return contentPane;
    }

    public Tab getTab() {
        return tab;
    }

    public void associateTab(Tab tab) {
        this.tab = tab;
    }
}
