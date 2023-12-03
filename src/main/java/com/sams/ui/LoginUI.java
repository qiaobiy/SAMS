package com.sams.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginUI extends VBox {
    private OnLoginSuccessListener onLoginSuccessListener;

    public LoginUI() {
        initializeUI();
    }

    public void setOnLoginSuccess(OnLoginSuccessListener listener) {
        this.onLoginSuccessListener = listener;
    }

    private void initializeUI() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Student Attendance Management System");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("root") && password.equals("142318")) {
                if (onLoginSuccessListener != null) {
                    onLoginSuccessListener.onLoginSuccess();
                }
            } else {
                Label errorLabel = new Label("Invalid username or password");
                errorLabel.setStyle("-fx-text-fill: red;");
                getChildren().add(errorLabel);
            }
        });

        getChildren().addAll(
                titleLabel,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                loginButton
        );
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess();
    }
}
