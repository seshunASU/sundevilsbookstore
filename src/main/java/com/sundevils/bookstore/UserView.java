package com.sundevils.bookstore;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public abstract class UserView extends View {
    protected TabPane tabPane;

    public UserView() {
        super();
        
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // TODO: style the tabs before creating account button
            // pages should have a showAccountButton boolean to toggle visibility
            
        AnchorPane anchor = new AnchorPane();
        // AnchorPane.setTopAnchor(hbox, 3.0);
        // AnchorPane.setRightAnchor(hbox, 5.0);
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        anchor.getChildren().add(tabPane);
        
        anchor.setId("rootPane");
        scene.setRoot(anchor);
    }

    @Override
    protected void setPage(Page page) {
        activePage = page;
        page.getTab().setContent(page.getContentPane());
        tabPane.getSelectionModel().select(page.getTab());
    }
}