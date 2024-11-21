package com.sundevils.bookstore;
import javafx.scene.control.TabPane;

public class UserView extends View {
    protected TabPane tabPane;

    public UserView() {
        super();
        tabPane = new TabPane(); // TODO: not have tabPane replace pane? | https://stackoverflow.com/questions/39205244/buttons-in-tabpane-using-javafx
        scene.setRoot(tabPane);
    }

    @Override
    protected void setPage(Page page) {
        activePage = page;
        // TODO: finish
        // tabPane.getChildren().clear();
        // tabPane.getChildren().add(page.getContentPane());
    }
}