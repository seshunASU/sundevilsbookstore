package com.sundevils.bookstore;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.util.Pair;

public abstract class View {
    // TODO: give all views & pages an ID

    protected Page activePage;
    protected StackPane pane;
    protected Button backBtn;

    protected AnchorPane anchor;

    public View() {
        pane = new StackPane();

        backBtn = new Button();
        backBtn.setPrefSize(40, 40);
        backBtn.setId("backBtn");
        backBtn.setCursor(Cursor.HAND);

        backBtn.setOnAction(e -> {
            Pair<Page, View> pageViewPair = App.getPageAndView(activePage.backPage);
            View targetView = pageViewPair.getValue();
            Page targetPage = pageViewPair.getKey();
            targetView.setPage(targetPage);
            
            App app = App.getInstance();
            app.setActiveView(targetView);
        });
        
        Text leftArrow = new Text("<"); // TODO: use image
        leftArrow.setFont(Font.font("", FontWeight.BOLD, 20));
        leftArrow.setFill(Color.web("#ffc425"));
        backBtn.setGraphic(leftArrow);
        
        anchor = new AnchorPane();
        anchor.setId("rootPane");
        anchor.getChildren().add(pane);

        AnchorPane.setTopAnchor(pane, 0.0);
        AnchorPane.setRightAnchor(pane, 0.0);
        AnchorPane.setLeftAnchor(pane, 0.0);
        AnchorPane.setBottomAnchor(pane, 0.0);
        AnchorPane.setTopAnchor(backBtn, 80.0);
        AnchorPane.setLeftAnchor(backBtn, 10.0);
    }

    public void activePageChanged() {
        if (activePage.backPage != null) {
            if (!anchor.getChildren().contains(backBtn)) {
                anchor.getChildren().add(backBtn);
            }
        } else {
            anchor.getChildren().remove(backBtn);
        }
    }

    public abstract void resetState(); // TODO: implement in phase 4

    public Pane getContentPane() {
        return anchor;
    }

    public String getActivePageTitle() {
        return activePage.getTitle();
    }

    public void registerPage(Page page) {
        App.registerPage(page, this);
    }
    
    protected void setPage(Page page) {
        activePage = page;
        pane.getChildren().clear();
        pane.getChildren().add(page.getContentPane());

        activePageChanged();
        App.updateWindowTitle();
    }
}
