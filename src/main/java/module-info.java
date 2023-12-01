module com.sams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sams to javafx.fxml;
    exports com.sams;
}