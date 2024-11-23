package com.sundevils.bookstore;

import javafx.scene.layout.Pane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public abstract class UserView extends View {
    protected TabPane tabPane;
    protected AnchorPane anchor;

    public UserView() {
        super();
        
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            
        anchor = new AnchorPane();
        // AnchorPane.setTopAnchor(hbox, 3.0);
        // AnchorPane.setRightAnchor(hbox, 5.0);
        AnchorPane.setTopAnchor(tabPane, 0.0);
        AnchorPane.setRightAnchor(tabPane, 0.0);
        AnchorPane.setLeftAnchor(tabPane, 0.0);
        AnchorPane.setBottomAnchor(tabPane, 0.0);
        anchor.setId("rootPane");

        anchor.getChildren().add(tabPane);
    
        // Tab click function
        tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
            if (newTab instanceof Tab) {
                activePage = ((Tab) newTab).activePage;
                App.updateWindowTitle();
            }
        });
    }

    @Override
    public Pane getContentPane() {
        return anchor;
    }

    @Override
    protected void setPage(Page page) {
        activePage = page;

        Tab pageTab = page.getTab();
        pageTab.setPage(page);;
        tabPane.getSelectionModel().select(pageTab);

        App.updateWindowTitle();
    }
}