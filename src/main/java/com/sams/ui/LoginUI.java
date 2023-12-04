package com.sams.ui;

import com.sams.service.UserService;
import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.Arrays;

public class LoginUI extends VBox {
    private OnLoginSuccessListener onLoginSuccessListener;
    private UserService userService;

    public LoginUI() {
        initializeUI();
        initializeServices();
    }

    public void setOnLoginSuccess(OnLoginSuccessListener listener) {
        this.onLoginSuccessListener = listener;
    }

    private void initializeUI() {
        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);

        // 创建一个旋转的图标
        SVGPath icon = new SVGPath();
        icon.setContent("M10 20 L10 10 L20 10 Z");
        icon.setFill(Color.WHITE);
        icon.setStroke(Color.DARKBLUE);
        icon.setStrokeWidth(2);

        // 创建一个旋转动画
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), icon);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setAutoReverse(true);
        rotateTransition.play();

        // 创建一个渐变背景
        Stop[] stops = {
                new Stop(0, Color.DARKBLUE),
                new Stop(1, Color.LIGHTBLUE)
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        BackgroundFill backgroundFill = new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        Label titleLabel = new Label("学生考勤管理系统");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);

        Label usernameLabel = new Label("账户:");
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("密码:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        Button loginButton = new Button("登录");
        loginButton.setPrefWidth(100);
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                if (userService.checkUser(username, password)) {
                    if (onLoginSuccessListener != null) {
                        onLoginSuccessListener.onLoginSuccess();
                    }
                } else {
                    Label errorLabel = new Label("账户或密码错误");
                    errorLabel.setStyle("-fx-text-fill: red;");
                    getChildren().add(errorLabel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // 处理数据库错误
            }
        });

        Button createUserButton = new Button("创建用户");
        createUserButton.setPrefWidth(100);
        createUserButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
        createUserButton.setOnAction(event -> {
            // 打开创建用户界面
            SignupUI signupUI = new SignupUI();
            Stage signupStage = new Stage();
            signupUI.show(signupStage);
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(loginButton, createUserButton);

        VBox.setMargin(titleLabel, new Insets(0, 0, 20, 0));

        // 设置旋转图标和渐变背景
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(icon, titleLabel);
        stackPane.setAlignment(Pos.CENTER);
        stackPane.setBackground(background);

        getChildren().addAll(
                stackPane,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                buttonBox
        );
    }

    private void initializeServices() {
        userService = new UserService();
    }

    public interface OnLoginSuccessListener {
        void onLoginSuccess();
    }
}
