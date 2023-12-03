package com.sams.service;

import com.sams.dao.AttendanceDAO;
import com.sams.model.AttendanceRecord;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AttendanceRecordService {
    private AttendanceDAO attendanceRecordDAO;

    public AttendanceRecordService(Connection connection) {
        attendanceRecordDAO = new AttendanceDAO(connection);
    }

    public void addAttendanceRecord(AttendanceRecord attendanceRecord) throws SQLException {
        attendanceRecordDAO.addAttendanceRecord(attendanceRecord);
    }

    public void updateAttendanceRecord(AttendanceRecord attendanceRecord) throws SQLException {
        attendanceRecordDAO.updateAttendanceRecord(attendanceRecord);
    }

    public void deleteAttendanceRecord(int recordID) throws SQLException {
        attendanceRecordDAO.deleteAttendanceRecord(recordID);
    }

    public List<AttendanceRecord> getAllAttendanceRecords() throws SQLException {
        return attendanceRecordDAO.getAllAttendanceRecords();
    }

    public AttendanceRecord getAttendanceRecordByID(int recordID) throws SQLException {
        return attendanceRecordDAO.getAttendanceRecordByID(recordID);
    }
}
