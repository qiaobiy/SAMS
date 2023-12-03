// com.sams.MainApp.java

package com.sams;

import com.sams.view.AttendanceTableView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        AttendanceTableView attendanceTableView = new AttendanceTableView();
        attendanceTableView.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
