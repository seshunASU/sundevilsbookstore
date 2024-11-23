package com.sundevils.bookstore;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public abstract class View {
    // TODO: give all views & pages an ID

    protected Page activePage;
    protected StackPane pane;

    public View() {
        pane = new StackPane();
        pane.setId("rootPane");
    }

    public Pane getContentPane() {
        return pane;
    }

    protected void setPage(Page page) {
        activePage = page;
        pane.getChildren().clear();
        pane.getChildren().add(page.getContentPane());
    }
}
