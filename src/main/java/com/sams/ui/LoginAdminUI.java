package com.sams.ui;

import com.sams.service.StudentService;
import com.sams.service.AttendanceRecordService;

import com.sams.util.DatabaseUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Connection;
public class LoginAdminUI extends Application {


    public LoginAdminUI(Connection connection) throws SQLException {
        this.studentService = new StudentService(connection);
    }
    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    private StudentService studentService;
    private AttendanceRecordService attendanceService;

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        studentService = new StudentService(DatabaseUtil.getConnection());
        attendanceService = new AttendanceRecordService(DatabaseUtil.getConnection());

        primaryStage.setTitle("Login Admin");

        // Login form
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            try {
                handleLoginButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        GridPane loginForm = new GridPane();
        loginForm.setHgap(10);
        loginForm.setVgap(10);
        loginForm.setAlignment(Pos.CENTER);
        loginForm.addRow(0, usernameLabel, usernameField);
        loginForm.addRow(1, passwordLabel, passwordField);
        loginForm.add(loginButton, 1, 2);

        VBox loginLayout = new VBox(20);
        loginLayout.setPadding(new Insets(20));
        loginLayout.getChildren().addAll(loginForm);

        Scene loginScene = new Scene(loginLayout, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void handleLoginButton() throws SQLException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
            showAdminUI();
        } else {
            showAlert("Invalid credentials", "Please enter valid username and password.");
        }
    }

    private void showAdminUI() throws SQLException {
        Stage adminStage = new Stage();
        adminStage.setTitle("Admin Panel");

        TabPane tabPane = new TabPane();
        Tab studentTab = new Tab("Student Management");
        Tab attendanceTab = new Tab("Attendance Management");

        studentTab.setContent(new StudentManagementUI(studentService));
        attendanceTab.setContent(new AttendanceManagementUI(attendanceService));

        tabPane.getTabs().addAll(studentTab, attendanceTab);

        Scene adminScene = new Scene(tabPane, 800, 600);
        adminStage.setScene(adminScene);
        adminStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
