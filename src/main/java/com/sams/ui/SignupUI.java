package com.sams.ui;

import com.sams.model.User;
import com.sams.service.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class SignupUI extends VBox {
    private UserService userService;

    public SignupUI() {
        userService = new UserService();
        initializeUI();
    }

    private void initializeUI() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("创建用户");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("账户:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("密码:");
        PasswordField passwordField = new PasswordField();

        Button saveButton = new Button("保存");
        saveButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                User user = new User(username, password);
                userService.saveUser(user);
                showSuccessMessage();
            } catch (SQLException e) {
                showErrorDialog("保存用户失败: " + e.getMessage());
            }
        });

        getChildren().addAll(
                titleLabel,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                saveButton
        );
    }

    private void showSuccessMessage() {
        Label successLabel = new Label("用户创建成功");
        successLabel.setStyle("-fx-text-fill: green;");
        getChildren().add(successLabel);
    }

    private void showErrorDialog(String message) {
        Label errorLabel = new Label(message);
        errorLabel.setStyle("-fx-text-fill: red;");
        getChildren().add(errorLabel);
    }

    public void show(Stage stage) {
        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.setTitle("创建用户");
        stage.show();
    }
}
