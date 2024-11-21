module com.sundevils.bookstore {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive java.sql;

    opens com.sundevils.bookstore to javafx.fxml;
    exports com.sundevils.bookstore;
}
