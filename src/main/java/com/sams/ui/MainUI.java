package com.sams.ui;

import com.sams.service.AttendanceRecordService;
import com.sams.service.StudentService;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.sql.SQLException;

public class MainUI extends VBox {
    private StudentService studentService;
    private AttendanceRecordService attendanceService;

    public MainUI(StudentService studentService, AttendanceRecordService attendanceService) {
        this.studentService = studentService;
        this.attendanceService = attendanceService;

        setSpacing(10);
        setPadding(new Insets(20));
        setAlignment(Pos.CENTER);
        setBackground(new Background(new BackgroundFill(Color.rgb(40, 40, 40), CornerRadii.EMPTY, Insets.EMPTY)));

        Button studentManagementButton = createButton("学生管理");
        studentManagementButton.setOnAction(event -> {
            try {
                openStudentManagementUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Button attendanceManagementButton = createButton("考勤管理");
        attendanceManagementButton.setOnAction(event -> {
            try {
                openAttendanceManagementUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        getChildren().addAll(studentManagementButton, attendanceManagementButton);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefWidth(200);
        button.setPrefHeight(50);
        button.setStyle("-fx-background-color: #E27D60; -fx-text-fill: white; -fx-font-size: 16px;");
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #E8A87C; -fx-text-fill: white; -fx-font-size: 16px;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: #E27D60; -fx-text-fill: white; -fx-font-size: 16px;");
        });
        return button;
    }

    private void openAttendanceManagementUI() throws SQLException {
        Stage attendanceManagementStage = new Stage();
        AttendanceManagementUI attendanceManagementUI = new AttendanceManagementUI(attendanceService);
        Scene attendanceManagementScene = new Scene(attendanceManagementUI, 800, 600);
        attendanceManagementStage.setScene(attendanceManagementScene);
        attendanceManagementStage.setTitle("考勤管理");

        // 添加淡入淡出的动画效果
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), attendanceManagementScene.getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        attendanceManagementStage.show();
    }

    private void openStudentManagementUI() throws SQLException {
        Stage studentManagementStage = new Stage();
        StudentManagementUI studentManagementUI = new StudentManagementUI(studentService);
        Scene studentManagementScene = new Scene(studentManagementUI, 800, 600);
        studentManagementStage.setScene(studentManagementScene);
        studentManagementStage.setTitle("学生管理");

        // 添加淡入淡出的动画效果
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), studentManagementScene.getRoot());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        studentManagementStage.show();
    }
}
