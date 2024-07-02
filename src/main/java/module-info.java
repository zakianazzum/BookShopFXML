module com.example.bookshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookshop to javafx.fxml;
    exports com.example.bookshop;
}