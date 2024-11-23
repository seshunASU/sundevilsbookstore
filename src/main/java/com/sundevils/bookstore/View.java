package com.sundevils.bookstore;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public abstract class View {
    // TODO: give all views & pages an ID

    protected Page activePage;
    protected StackPane pane;
    protected Scene scene;

    public View() {
        pane = new StackPane();
        scene = new Scene(pane);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        pane.setId("rootPane");
    }

    public Scene getScene() {
        return scene;
    }

    protected void setPage(Page page) {
        activePage = page;
        pane.getChildren().clear();
        pane.getChildren().add(page.getContentPane());
    }
}
