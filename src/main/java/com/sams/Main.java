package com.sams;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("登录");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nameLabel = new Label("用户名:");
        TextField nameInput = new TextField("admin");
        nameInput.setPromptText("输入用户名");
        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameInput, 1, 0);
        grid.getChildren().addAll(nameLabel, nameInput);

        Label passLabel = new Label("密码:");
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("输入密码");
        GridPane.setConstraints(passLabel, 0, 1);
        GridPane.setConstraints(passInput, 1, 1);
        grid.getChildren().addAll(passLabel, passInput);

        Button loginButton = new Button("登录");
        GridPane.setConstraints(loginButton, 1, 2);
        grid.getChildren().add(loginButton);

        loginButton.setOnAction(e -> {
            if (nameInput.getText().equals("admin") && passInput.getText().equals("admin")) {
                System.out.println("登录成功");
                // TODO: 进入管理界面
            } else {
                System.out.println("用户名或密码错误");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
