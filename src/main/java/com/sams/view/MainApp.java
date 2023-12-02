package com.sams.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("学生考勤系统");

        Button studentButton = new Button("学生列表");
        studentButton.setOnAction(e -> {
            // 在此处添加显示学生列表的代码
        });

        Button attendanceButton = new Button("考勤记录列表");
        attendanceButton.setOnAction(e -> {
            // 在此处添加显示考勤记录列表的代码
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(studentButton, attendanceButton);

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
