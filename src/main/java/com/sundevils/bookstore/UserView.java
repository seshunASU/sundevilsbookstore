package com.sundevils.bookstore;
import javafx.scene.control.TabPane;

public class UserView extends View {
    protected TabPane tabPane;

    public UserView() {
        super();
        tabPane = new TabPane(); // TODO: not have tabPane replace pane?
        scene.setRoot(tabPane);
    }
}
