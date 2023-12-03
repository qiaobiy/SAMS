package com.sams.ui;

import com.sams.service.AttendanceRecordService;
import com.sams.service.StudentService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

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

        Button studentManagementButton = new Button("Student Management");
        studentManagementButton.setOnAction(event -> {
            try {
                StudentManagementUI studentManagementUI = new StudentManagementUI(studentService);
                getScene().setRoot(studentManagementUI);
            } catch (SQLException ex) {
                // 处理异常，例如打印错误信息或显示错误对话框
                ex.printStackTrace();
            }
        });

        Button attendanceManagementButton = new Button("Attendance Management");
        attendanceManagementButton.setOnAction(event -> {
            try {
                AttendanceManagementUI attendanceManagementUI = new AttendanceManagementUI(attendanceService);
                getScene().setRoot(attendanceManagementUI);
            } catch (SQLException ex) {
                // 处理异常，例如打印错误信息或显示错误对话框
                ex.printStackTrace();
            }
        });

        getChildren().addAll(studentManagementButton, attendanceManagementButton);
    }
}
