module com.sams {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens com.sams to javafx.fxml;
    exports com.sams;

    opens com.sams.ui to javafx.fxml;
}