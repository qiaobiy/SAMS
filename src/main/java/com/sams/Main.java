package com.sams;

import com.sams.service.AttendanceRecordService;
import com.sams.service.StudentService;
import com.sams.ui.AttendanceManagementUI;
import com.sams.ui.StudentManagementUI;

import com.sams.util.DatabaseUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {


        // Create services
        StudentService studentService = new StudentService(DatabaseUtil.getConnection());
        AttendanceRecordService attendanceService = new AttendanceRecordService(DatabaseUtil.getConnection());

        // Create UI components
        StudentManagementUI studentManagementUI = new StudentManagementUI(studentService);
        AttendanceManagementUI attendanceManagementUI = new AttendanceManagementUI(attendanceService);

        // Create main layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(studentManagementUI);
        mainLayout.setRight(attendanceManagementUI);

        // Create scene
        Scene scene = new Scene(mainLayout, 800, 600);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Attendance Management System");
        primaryStage.show();
    }
}
