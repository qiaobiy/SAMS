package com.sams;

import com.sams.service.AttendanceRecordService;
import com.sams.service.StudentService;
import com.sams.ui.AttendanceManagementUI;
import com.sams.ui.LoginUI;
import com.sams.ui.MainUI;
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
        Connection connection = DatabaseUtil.getConnection();
        StudentService studentService = new StudentService(connection);
        AttendanceRecordService attendanceService = new AttendanceRecordService(connection);

        // Create login UI
        LoginUI loginUI = new LoginUI();
        loginUI.setOnLoginSuccess(() -> {
            // Create main UI
            MainUI mainUI = new MainUI(studentService, attendanceService);

            // Create main layout
            BorderPane mainLayout = new BorderPane();
            mainLayout.setCenter(mainUI);

            // Create scene
            Scene scene = new Scene(mainLayout, 800, 600);

            // Set scene and show stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Student Attendance Management System");
            primaryStage.show();
        });

        // Create scene
        Scene scene = new Scene(loginUI, 400, 300);

        // Set scene and show stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();
    }
}
