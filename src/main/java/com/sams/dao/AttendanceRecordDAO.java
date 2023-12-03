package com.sams.dao;

import com.sams.model.AttendanceRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRecordDAO {
    private Connection connection;

    public AttendanceRecordDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAttendanceRecord(AttendanceRecord attendanceRecord) throws SQLException {
        String query = "INSERT INTO attendancerecord (RecordID, Name, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, attendanceRecord.getRecordID());
            statement.setString(2, attendanceRecord.getName());
            statement.setString(3, attendanceRecord.getStudentID());
            statement.setString(4, attendanceRecord.getDate());
            statement.setString(5, attendanceRecord.getPeriod());
            statement.setString(6, attendanceRecord.getCourse());
            statement.setString(7, attendanceRecord.getType());
            statement.executeUpdate();
        }
    }

    public void updateAttendanceRecord(AttendanceRecord attendanceRecord) throws SQLException {
        String query = "UPDATE attendancerecord SET Name = ?, StudentID = ?, Date = ?, Period = ?, Course = ?, Type = ? WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, attendanceRecord.getName());
            statement.setString(2, attendanceRecord.getStudentID());
            statement.setString(3, attendanceRecord.getDate());
            statement.setString(4, attendanceRecord.getPeriod());
            statement.setString(5, attendanceRecord.getCourse());
            statement.setString(6, attendanceRecord.getType());
            statement.setInt(7, attendanceRecord.getRecordID());
            statement.executeUpdate();
        }
    }

    public void deleteAttendanceRecord(int recordID) throws SQLException {
        String query = "DELETE FROM attendancerecord WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordID);
            statement.executeUpdate();
        }
    }

    public List<AttendanceRecord> getAllAttendanceRecords() throws SQLException {
        List<AttendanceRecord> attendanceRecords = new ArrayList<>();
        String query = "SELECT * FROM attendancerecord";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int recordID = resultSet.getInt("RecordID");
                String name = resultSet.getString("Name");
                String studentID = resultSet.getString("StudentID");
                String date = resultSet.getString("Date");
                String period = resultSet.getString("Period");
                String course = resultSet.getString("Course");
                String type = resultSet.getString("Type");
                AttendanceRecord attendanceRecord = new AttendanceRecord(recordID, name, studentID, date, period, course, type);
                attendanceRecords.add(attendanceRecord);
            }
        }
        return attendanceRecords;
    }

    public AttendanceRecord getAttendanceRecordByID(int recordID) throws SQLException {
        String query = "SELECT * FROM attendancerecord WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String studentID = resultSet.getString("StudentID");
                    String date = resultSet.getString("Date");
                    String period = resultSet.getString("Period");
                    String course = resultSet.getString("Course");
                    String type = resultSet.getString("Type");
                    return new AttendanceRecord(recordID, name, studentID, date, period, course, type);
                }
            }
        }
        return null;
    }
}
