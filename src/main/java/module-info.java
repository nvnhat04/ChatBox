module com.app.dictionaryproject {
    requires javafx.fxml;
    requires  javafx.controls;
    requires javafx.web;
    opens org.example;
    exports org.example;
}