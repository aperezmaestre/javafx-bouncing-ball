module com.lab5work.lab5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lab5work.lab5 to javafx.fxml;
    exports com.lab5work.lab5;
}