package com.sundevils.bookstore;

import javafx.scene.layout.Pane;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public abstract class UserView extends View {
    protected TabPane tabPane;
    protected AnchorPane anchor;
    protected Button signOutBtn;

    public UserView() {
        super();
        
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        signOutBtn = new Button("Sign out");
		signOutBtn.setStyle("-fx-font-size: 16px; -fx-background-color: #8e0c3a; -fx-text-fill: gold;");
        signOutBtn.setCursor(Cursor.HAND);

        signOutBtn.setOnAction(e -> {
            App app = App.getInstance();
            app.resetState();
            app.setActiveView(app.loginView);
        });

        anchor = new AnchorPane();
        AnchorPane.setTopAnchor(signOutBtn, 7.0);
        AnchorPane.setRightAnchor(signOutBtn, 0.0);
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
                activePageChanged();
                App.updateWindowTitle();
            }
        });
    }

    public void activePageChanged() {
        if (activePage.showSignoutButton) {
            if (!anchor.getChildren().contains(signOutBtn)) {
                anchor.getChildren().add(signOutBtn);
            }
        } else {
            anchor.getChildren().remove(signOutBtn);
        }
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

        activePageChanged();
        App.updateWindowTitle();
    }
}