module com.sundevils.bookstore {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sundevils.bookstore to javafx.fxml;
    exports com.sundevils.bookstore;
}
