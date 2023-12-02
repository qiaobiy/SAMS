package com.sams.controller;

import com.sams.dao.AttendanceRecordDao;
import com.sams.model.AttendanceRecord;

import java.sql.Date;
import java.util.List;

public class AttendanceRecordController {
    private AttendanceRecordDao attendanceRecordDao;

    public AttendanceRecordController() {
        attendanceRecordDao = new AttendanceRecordDao();
    }

    public List<AttendanceRecord> getAllAttendanceRecords() {
        return attendanceRecordDao.getAllAttendanceRecords();
    }

    public void addAttendanceRecord(int recordID, String studentID, Date date, String period, String course, String type) {
        // 创建AttendanceRecord对象并设置属性
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setRecordID(recordID);
        attendanceRecord.setStudentID(studentID);
        attendanceRecord.setDate(date);
        attendanceRecord.setPeriod(period);
        attendanceRecord.setCourse(course);
        attendanceRecord.setType(type);

        // 调用Dao层方法插入考勤记录
        AttendanceRecordDao attendanceRecordDao = new AttendanceRecordDao();
        attendanceRecordDao.addAttendanceRecord(attendanceRecord);
    }

    // 其他业务逻辑方法，如添加考勤记录、统计缺勤次数等
}
