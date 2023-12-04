package com.sams.ui;

import com.sams.service.AttendanceRecordService;
import com.sams.service.StudentService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

        Button studentManagementButton = new Button("学生管理");
        studentManagementButton.setOnAction(event -> {
            try {
                openStudentManagementUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        Button attendanceManagementButton = new Button("考勤管理");
        attendanceManagementButton.setOnAction(event -> {
            try {
                openAttendanceManagementUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        getChildren().addAll(studentManagementButton, attendanceManagementButton);
    }

    private void openAttendanceManagementUI() throws SQLException {
        Stage attendanceManagementStage = new Stage();
        AttendanceManagementUI attendanceManagementUI = new AttendanceManagementUI(attendanceService);
        Scene attendanceManagementScene = new Scene(attendanceManagementUI, 800, 600);
        attendanceManagementStage.setScene(attendanceManagementScene);
        attendanceManagementStage.setTitle("考勤管理");

        attendanceManagementStage.show();
    }

    private void openStudentManagementUI() throws SQLException {
        Stage studentManagementStage = new Stage();
        StudentManagementUI studentManagementUI = new StudentManagementUI(studentService);
        Scene studentManagementScene = new Scene(studentManagementUI, 800, 600);
        studentManagementStage.setScene(studentManagementScene);
        studentManagementStage.setTitle("学生管理");

        studentManagementStage.show();
    }

}