package com.sundevils.bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ActiveListingsPage extends Page {
	public class Listing {
		String status;
		String title;
		String condition;
		double sellPrice;
		int quantity;		
	
        public Listing(String title, String condition, double sellPrice, int quantity, String status) {
            this.title = title;
            this.condition = condition;
            this.sellPrice = sellPrice;
            this.quantity = quantity;
            this.status = status;
        }
        
        
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public double getSellPrice() {
            return sellPrice;
        }

        public void setSellPrice(double sellPrice) {
            this.sellPrice = sellPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
	}
	
	public ActiveListingsPage() {
		title = "Active Listings";
		showSignoutButton = true;
		
		Button newListingButton = new Button("New Listing");
		newListingButton.setStyle("-fx-font-size: 20px; -fx-background-color: #ffc425; -fx-text-fill: #8e0c3a;");
		newListingButton.setCursor(Cursor.HAND);
        
        newListingButton.setOnAction(e -> {
            SellerView sellerView = App.getInstance().sellerView;
            sellerView.setPage(sellerView.newListingsPage);
        });

		HBox newListing = new HBox(newListingButton);
		newListing.setPadding(new Insets(35));
		newListing.setAlignment(Pos.BOTTOM_RIGHT);
		
		TableView<Listing> listingsTable = new TableView<Listing>();
		TableColumn<Listing, String> statusCol = new TableColumn<Listing, String>("Status");
		TableColumn<Listing, String> titleCol = new TableColumn<Listing, String>("Title");
		TableColumn<Listing, String> conditionCol = new TableColumn<>("Condition");
		TableColumn<Listing, Double> sellPriceCol = new TableColumn<Listing, Double>("Sell Price");
		TableColumn<Listing, Integer> priceCol = new TableColumn<Listing, Integer>("Quantity");
		TableColumn<Listing, Void> deleteCol = new TableColumn<Listing, Void>("");
		
		
		titleCol.setMinWidth(200);
		conditionCol.setMinWidth(140);
		
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		conditionCol.setCellValueFactory(new PropertyValueFactory<>("condition"));
        sellPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        applyTextColor(statusCol);
        applyTextColor(titleCol);
        applyTextColor(conditionCol);
        applyTextColor(sellPriceCol);
        applyTextColor(priceCol);        
           
        listingsTable.setRowFactory(tv -> {
            return new TableRow<Listing>() {
                @Override
                protected void updateItem(Listing item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (empty || item == null) {
                    	
                        if (getIndex() % 2 == 0) {
                            setStyle("-fx-background-color: #8e0c3a;");
                            
                        } 
                        else {
                            setStyle("-fx-background-color: #72022a;");
                        }
                    } 
                    else {
                    	if (getIndex() % 2 == 0) {
                            setStyle("-fx-background-color: #8e0c3a; -fx-text-fill: #ffc425;");
                            
                        } 
                        else {
                            setStyle("-fx-background-color: #72022a; -fx-text-fill: #ffc425;");
                        }                    	
                    }
                }
            };
        });
        
        deleteCol.setCellFactory(col -> {
            TableCell<Listing, Void> cell = new TableCell<Listing, Void>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Listing rowData = getTableView().getItems().get(getIndex());
                        getTableView().getItems().remove(rowData);
                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
            return cell;
        });
        
		@SuppressWarnings("unchecked")
		TableColumn<Listing, ?>[] columns = new TableColumn[] {statusCol, titleCol, conditionCol, sellPriceCol, priceCol, deleteCol};
		listingsTable.getColumns().addAll(columns);
		listingsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		
		ObservableList<Listing> bookData = FXCollections.observableArrayList(
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active"),
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active"),
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active"),
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active"),
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active"),
            new Listing("Book 1", "Used, Like New", 20.00, 5, "Pending"),
            new Listing("Book 2", "Heavily Used", 10.00, 10, "Active"),
            new Listing("Book 3", "Used, Like New", 25.00, 5, "Pending"),
            new Listing("Book 4", "Moderately Used", 25.00, 12, "Active")
        );
		
		listingsTable.setItems(bookData);
				
		VBox vbox = new VBox(listingsTable, newListing);
		vbox.setPadding(new Insets(70, 50, 0, 50));
		
		contentPane.getChildren().add(vbox);
	}

	private <T> void applyTextColor(TableColumn<Listing, T> column) {
	    column.setCellFactory(col -> new TableCell<Listing, T>() {
	        @Override
	        protected void updateItem(T item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty) {
	                setText(null);
	            } else {
	                setText(item.toString());
	                setStyle("-fx-text-fill: #FFC425");
	            }
	        }
	    });
	}
}
