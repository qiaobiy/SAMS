package com.sams.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginUI extends VBox {
    private OnLoginSuccessListener onLoginSuccessListener;
    private CheckBox rememberMeCheckBox;
    private boolean rememberMe;

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

        Label titleLabel = new Label("学生考勤管理系统");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label usernameLabel = new Label("账户:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("密码:");
        PasswordField passwordField = new PasswordField();

        rememberMeCheckBox = new CheckBox("记住账号");
        rememberMe = false;
        rememberMeCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            rememberMe = newValue;
        });

        Button loginButton = new Button("登录");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (username.equals("") && password.equals("")) {
                if (rememberMe) {
                    // 保存用户名和密码到本地文件或数据库
                    // ...
                }

                if (onLoginSuccessListener != null) {
                    onLoginSuccessListener.onLoginSuccess();
                }
            } else {
                Label errorLabel = new Label("账户或密码错误");
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
                rememberMeCheckBox,
                loginButton
        );
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess();
    }
}
