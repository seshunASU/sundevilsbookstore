import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
		
		Button accountButton = new Button("Account");
		accountButton.setStyle("-fx-font-size: 20px; -fx-background-color: #8e0c3a; -fx-text-fill: gold;");
		
		HBox account = new HBox(accountButton);
		account.setPadding(new Insets(35));
		account.setAlignment(Pos.TOP_RIGHT);
		
		
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
        
        
        @SuppressWarnings("unchecked")
		TableColumn<SalesHistory, ?>[] columns = new TableColumn[] {dateCol, titleCol, conditionCol, sellPriceCol, buyerCol};
		historyTable.getColumns().addAll(columns);
		historyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
		ObservableList<SalesHistory> historyData = FXCollections.observableArrayList(
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson"),
	            new SalesHistory("2024-11-22", "Book Title 1", "New", 19.99, "John Doe"),
	            new SalesHistory("2024-10-10", "Book Title 2", "Used", 12.50, "Jane Smith"),
	            new SalesHistory("2024-01-15", "Book Title 3", "Like New", 22.00, "Mary Johnson")
				);

		
		historyTable.setItems(historyData);
		
		Text totalEarnings = new Text("Total Earnings:");
		totalEarnings.setFont(Font.font("", FontWeight.BOLD, 42));
        totalEarnings.setFill(Color.GOLD);
		
        Text dollarSign = new Text(" $");
        dollarSign.setFont(Font.font("", FontWeight.BOLD, 42));
        dollarSign.setFill(Color.web("#8e0c3a"));
		
        Text priceText = new Text("285.00");
        priceText.setFont(Font.font("", FontWeight.BOLD, 42));
        priceText.setFill(Color.GOLD);
        
        HBox priceH = new HBox(totalEarnings, dollarSign, priceText);
		
		VBox vbox = new VBox(account, historyTable, priceH);
		vbox.setPadding(new Insets(70, 50, 0, 50));
		
		contentPane.getChildren().add(vbox);
		contentPane.setStyle("-fx-background-color: #191919;"); 
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
