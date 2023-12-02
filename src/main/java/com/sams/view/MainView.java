package com.sams.view;

import com.sams.controller.AttendanceRecordController;
import com.sams.model.AttendanceRecord;

import java.util.List;

public class MainView {
    public static void main(String[] args) {
        AttendanceRecordController attendanceRecordController = new AttendanceRecordController();
        List<AttendanceRecord> attendanceRecords = attendanceRecordController.getAllAttendanceRecords();

        // 在这里可以进行对考勤记录的展示或其他操作
        for (AttendanceRecord record : attendanceRecords) {
            System.out.println(record);
        }
    }
}
