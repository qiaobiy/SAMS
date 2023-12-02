package com.sams;

import com.sams.controller.AttendanceRecordController;
import com.sams.model.AttendanceRecord;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;



public class Main {
    public static void main(String[] args) {
        AttendanceRecordController attendanceRecordController = new AttendanceRecordController();
        List<AttendanceRecord> attendanceRecords = attendanceRecordController.getAllAttendanceRecords();

        // 在这里可以进行对考勤记录的展示或其他操作
        for (AttendanceRecord record : attendanceRecords) {
            System.out.println(record);
        }
    }

    public void showScene() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
