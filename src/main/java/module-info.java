module com.sams {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.sams to javafx.fxml;
    exports com.sams;
    exports com.sams.view;
    opens com.sams.view to javafx.fxml;
}