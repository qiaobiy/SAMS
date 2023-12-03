package com.sams;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminLoginApp extends Application {

    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    private TextField usernameField;
    private PasswordField passwordField;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admin Login");

        // 创建登录界面
        VBox loginBox = new VBox(10);
        loginBox.setPadding(new Insets(20));
        loginBox.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username:");
        usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> login());

        loginBox.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton);

        Scene loginScene = new Scene(loginBox, 300, 200);

        // 创建主界面
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab studentTab = new Tab("学生管理");
        studentTab.setContent(new Label("学生管理系统"));

        Tab attendanceTab = new Tab("考勤管理");
        attendanceTab.setContent(new Label("考勤管理系统"));

        tabPane.getTabs().addAll(studentTab, attendanceTab);

        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(tabPane);

        Scene mainScene = new Scene(mainPane, 800, 600);

        // 设置初始界面为登录界面
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.equals(DEFAULT_USERNAME) && password.equals(DEFAULT_PASSWORD)) {
            // 登录成功，切换到主界面
            Stage primaryStage = (Stage) usernameField.getScene().getWindow();
//            primaryStage.setScene(mainScene);
        } else {
            // 登录失败，显示错误提示
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password");
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
