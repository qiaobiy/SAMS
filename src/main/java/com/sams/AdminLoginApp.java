package com.sams;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class AdminLoginApp extends Application {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/sams";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "142318";

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin";

    private Connection connection;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Login");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        gridPane.add(usernameLabel, 0, 0);
        gridPane.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        gridPane.add(loginButton, 1, 2);

        Scene loginScene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals(DEFAULT_ADMIN_USERNAME) && password.equals(DEFAULT_ADMIN_PASSWORD)) {
                showAdminFunctionality(primaryStage);
            } else {
                showAlert("Invalid credentials", "Please enter correct username and password.");
            }
        });
    }

    private void showAdminFunctionality(Stage primaryStage) {
        primaryStage.setTitle("Admin Functionality");

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab addStudentTab = new Tab("Add Student");
        addStudentTab.setContent(new Label("Add Student functionality goes here."));
        tabPane.getTabs().add(addStudentTab);
        // 在showAdminFunctionality方法中的addStudentTab.setContent中添加以下代码

        GridPane addStudentPane = new GridPane();
        addStudentPane.setAlignment(Pos.CENTER);
        addStudentPane.setHgap(10);
        addStudentPane.setVgap(10);
        addStudentPane.setPadding(new Insets(25, 25, 25, 25));

        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdField = new TextField();
        addStudentPane.add(studentIdLabel, 0, 0);
        addStudentPane.add(studentIdField, 1, 0);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        addStudentPane.add(nameLabel, 0, 1);
        addStudentPane.add(nameField, 1, 1);

        Label genderLabel = new Label("Gender:");
        TextField genderField = new TextField();
        addStudentPane.add(genderLabel, 0, 2);
        addStudentPane.add(genderField, 1, 2);

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();
        addStudentPane.add(ageLabel, 0, 3);
        addStudentPane.add(ageField, 1, 3);

        Label classLabel = new Label("Class:");
        TextField classField = new TextField();
        addStudentPane.add(classLabel, 0, 4);
        addStudentPane.add(classField, 1, 4);

        Button addButton = new Button("Add");
        addStudentPane.add(addButton, 1, 5);

        addStudentTab.setContent(addStudentPane);

        addButton.setOnAction(event -> {
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            String gender = genderField.getText();
            int age = Integer.parseInt(ageField.getText());
            String studentClass = classField.getText();

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO studenttable (StudentID, Name, Gender, Age, StudentClass) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, studentId);
                statement.setString(2, name);
                statement.setString(3, gender);
                statement.setInt(4, age);
                statement.setString(5, studentClass);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    showAlert("Success", "Student added successfully.");
                    studentIdField.clear();
                    nameField.clear();
                    genderField.clear();
                    ageField.clear();
                    classField.clear();
                } else {
                    showAlert("Error", "Failed to add student.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while adding student.");
            }
        });


        Tab searchStudentTab = new Tab("Search Student");
        searchStudentTab.setContent(new Label("Search Student functionality goes here."));
        tabPane.getTabs().add(searchStudentTab);

        Tab viewStudentTableTab = new Tab("View Student Table");
        viewStudentTableTab.setContent(new Label("View Student Table functionality goes here."));
        tabPane.getTabs().add(viewStudentTableTab);

        Tab addAttendanceRecordTab = new Tab("Add AttendanceRecord");

        GridPane addAttendanceRecordPane = new GridPane();
        addAttendanceRecordPane.setAlignment(Pos.CENTER);
        addAttendanceRecordPane.setHgap(20);
        addAttendanceRecordPane.setVgap(20);
        addAttendanceRecordPane.setPadding(new Insets(25, 25, 25, 25));

        Label recordIDLabel = new Label("Record ID:");
        TextField recordIDField = new TextField();
        addAttendanceRecordPane.add(recordIDLabel, 0, 0);
        addAttendanceRecordPane.add(recordIDField, 1, 0);

        Label studentIdRecordLabel = new Label("Student ID:");
        TextField studentIdRecordField = new TextField();
        addAttendanceRecordPane.add(studentIdRecordLabel, 0, 1);
        addAttendanceRecordPane.add(studentIdRecordField, 1, 1);

        Label dateLabel = new Label("Date:");
        TextField dateField = new TextField();
        addAttendanceRecordPane.add(dateLabel, 0, 2);
        addAttendanceRecordPane.add(dateField, 1, 2);

        Label periodLabel = new Label("Period:");
        TextField periodField = new TextField();
        addAttendanceRecordPane.add(periodLabel, 0, 3);
        addAttendanceRecordPane.add(periodField, 1, 3);

        Label courseLabel = new Label("Course:");
        TextField courseField = new TextField();
        addAttendanceRecordPane.add(courseLabel, 0, 4);
        addAttendanceRecordPane.add(courseField, 1, 4);

        Label typeLabel = new Label("Type:");
        TextField typeField = new TextField();
        addAttendanceRecordPane.add(typeLabel, 0, 5);
        addAttendanceRecordPane.add(typeField, 1, 5);

        Button addButton1 = new Button("Add");
        addAttendanceRecordPane.add(addButton1, 1, 6);

        addAttendanceRecordTab.setContent(addAttendanceRecordPane);
        tabPane.getTabs().add(addAttendanceRecordTab);

        addButton1.setOnAction(event -> {
            int recordID = Integer.parseInt(recordIDField.getText());
            String studentId = studentIdRecordField.getText();
            String date = dateField.getText();
            String period = periodField.getText();
            String course = courseField.getText();
            String type = typeField.getText();

            try {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO attendancerecords (RecordID, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?)");
                statement.setInt(1, recordID);
                statement.setString(2, studentId);
                statement.setString(3, date);
                statement.setString(4, period);
                statement.setString(5, course);
                statement.setString(6, type);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    showAlert("Success", "Attendance record added successfully.");
                    recordIDField.clear();
                    studentIdRecordField.clear();
                    dateField.clear();
                    periodField.clear();
                    courseField.clear();
                    typeField.clear();
                } else {
                    showAlert("Error", "Failed to add attendance record.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred while adding attendance record.");
            }
        });




        Tab viewAttendanceTableTab = new Tab("View Attendance Table");
        viewAttendanceTableTab.setContent(new Label("View Attendance Table functionality goes here."));
        tabPane.getTabs().add(viewAttendanceTableTab);

        Scene adminScene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(adminScene);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void init() throws Exception {
        super.init();
        connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        connection.close();
    }
}
