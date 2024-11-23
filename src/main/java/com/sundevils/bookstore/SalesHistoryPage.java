package com.sundevils.bookstore;

import com.sundevils.bookstore.SalesHistoryPage.SalesHistory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SalesHistoryPage extends Page {
	public class SalesHistory {
		String date;
		String title;
		String condition;
		double sellPrice;
		String buyer;

		public SalesHistory(String date, String title, String condition, double sellPrice, String buyer) {
			this.date = date;
	        this.title = title;
	        this.condition = condition;
	        this.sellPrice = sellPrice;
	        this.buyer = buyer;
	    }
	    public String getDate() {
	        return date;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public String getCondition() {
	        return condition;
	    }

	    public double getSellPrice() {
	        return sellPrice;
	    }

	    public String getBuyer() {
	        return buyer;
	    }
	}
	
	public SalesHistoryPage() {
		title = "Sales History";
		showSignoutButton = true;
		
		TableView<SalesHistory> historyTable = new TableView<SalesHistory>();
		TableColumn<SalesHistory, String> dateCol = new TableColumn<SalesHistory, String>("Date");
		TableColumn<SalesHistory, String> titleCol = new TableColumn<SalesHistory, String>("Title");
		TableColumn<SalesHistory, String> conditionCol = new TableColumn<>("Condition");
		TableColumn<SalesHistory, Double> sellPriceCol = new TableColumn<SalesHistory, Double>("Sell Price");
		TableColumn<SalesHistory, String> buyerCol = new TableColumn<SalesHistory, String>("Buyer");
		
		titleCol.setMinWidth(200);
		conditionCol.setMinWidth(140);
		
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		conditionCol.setCellValueFactory(new PropertyValueFactory<>("condition"));
        sellPriceCol.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        buyerCol.setCellValueFactory(new PropertyValueFactory<>("buyer"));
		
        applyTextColor(dateCol);
        applyTextColor(titleCol);
        applyTextColor(conditionCol);
        applyTextColor(sellPriceCol);
        applyTextColor(buyerCol); 
		
        historyTable.setRowFactory(tv -> {
            return new TableRow<SalesHistory>() {
                @Override
                protected void updateItem(SalesHistory item, boolean empty) {
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
        
        // Iterate history log
		App app = App.getInstance();
		for (SalesHistoryLog log : app.salesHistoryDB.getHistory(3)) {
            // ...
        }

        @SuppressWarnings("unchecked")
		TableColumn<SalesHistory, ?>[] columns = new TableColumn[] {dateCol, titleCol, conditionCol, sellPriceCol, buyerCol};
		historyTable.getColumns().addAll(columns);
		historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		
		ObservableList<SalesHistory> historyData = FXCollections.observableArrayList(
			new SalesHistory("2024-11-22", "Advanced Python", "Used, Like New", 20.00, "John Doe"),
			new SalesHistory("2024-10-10", "Advanced Chemistry", "Heavily Used", 12.00, "Jane Smith"),
			new SalesHistory("2024-01-15", "Book Title 3", "Moderately Used", 22.00, "Mary Johnson"),
			new SalesHistory("2024-09-01", "Science for All", "New", 30.00, "James Brown"),
			new SalesHistory("2023-12-05", "Mathematics Simplified", "Used", 15.50, "Emily Davis"),
			new SalesHistory("2024-07-19", "Programming 101", "Like New", 18.00, "Michael Clark"),
			new SalesHistory("2024-03-12", "Advanced Physics", "New", 45.00, "Sarah Wilson"),
			new SalesHistory("2024-05-25", "Novel Ideas", "Used", 10.75, "Chris Harris"),
			new SalesHistory("2024-06-03", "Machine Learning Basics", "Like New", 28.00, "Rebecca Lewis"),
			new SalesHistory("2024-02-20", "The Great Adventure", "New", 19.99, "David King"),
			new SalesHistory("2024-04-11", "History of Art", "Used", 23.50, "Laura Scott"),
			new SalesHistory("2023-11-15", "Culinary Arts Guide", "Like New", 16.75, "Alice Adams"),
			new SalesHistory("2024-08-30", "The Art of War", "Used", 12.50, "George Martin"),
			new SalesHistory("2024-03-27", "Introduction to AI", "Like New", 35.00, "Jack Miller"),
			new SalesHistory("2024-10-22", "Fictional Worlds", "New", 25.00, "Olivia Harris"),
			new SalesHistory("2024-01-01", "Understanding Economics", "Used", 17.99, "William Young"));

		double totalEarnings = 0;
        for (SalesHistory sale : historyData) {
            totalEarnings += sale.getSellPrice(); 
        }

		historyTable.setItems(historyData);
		
		Text totalEarningsText = new Text("Total Earnings:");
		totalEarningsText.setFont(Font.font("", FontWeight.BOLD, 42));
        totalEarningsText.setFill(Color.GOLD);
		
        Text dollarSign = new Text(" $");
        dollarSign.setFont(Font.font("", FontWeight.BOLD, 42));
        dollarSign.setFill(Color.web("#8e0c3a"));
		
        Text priceText = new Text(String.format("%.2f", totalEarnings));
        priceText.setFont(Font.font("", FontWeight.BOLD, 42));
        priceText.setFill(Color.GOLD);
        
        HBox priceH = new HBox(totalEarnings, dollarSign, priceText);
		
		VBox vbox = new VBox(historyTable, priceH);
		vbox.setPadding(new Insets(70, 50, 0, 50));
		
		contentPane.getChildren().add(vbox);
	}
	
	private <T> void applyTextColor(TableColumn<SalesHistory, T> column) {
	    column.setCellFactory(col -> new TableCell<SalesHistory, T>() {
	        @Override
	        protected void updateItem(T item, boolean empty) {
	            super.updateItem(item, empty);
	            if (empty || item == null) {
	                setText(null);
	                setStyle(null);
	            } else {
	                setText(item.toString());
	                setStyle("-fx-text-fill: #FFC425");
	            }
	        }
	    });
	}
}

