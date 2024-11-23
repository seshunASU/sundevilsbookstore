package com.sundevils.bookstore;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class Page {
    protected StackPane contentPane;
    protected String title; // TODO: set window title to this
    protected Tab tab;

    protected boolean showBackButton;
    protected boolean showAccountButton;

    public Page() {
        contentPane = new StackPane();
        title = "";
    }

    public Pane getContentPane() {
        return contentPane;
    }

    public Tab getTab() {
        return tab;
    }

    public void associateTab(Tab tab) {
        this.tab = tab;

        if (tab.getContent() == null) {
            tab.setContent(contentPane);
        }
    }
}
