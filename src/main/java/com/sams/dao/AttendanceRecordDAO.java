package com.sams.dao;

import com.sams.model.AttendanceRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRecordDAO {
    private Connection connection;

    public AttendanceRecordDAO() {
        this.connection = connection;
    }

    public void addAttendanceRecord(AttendanceRecord record) throws SQLException {
        String query = "INSERT INTO attendance_records (RecordID, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getRecordID());
            statement.setInt(2, record.getStudentID());
            statement.setDate(3, record.getDate());
            statement.setString(4, record.getPeriod());
            statement.setString(5, record.getCourse());
            statement.setString(6, record.getType());
            statement.executeUpdate();
        }
    }

    public void updateAttendanceRecord(AttendanceRecord record) throws SQLException {
        String query = "UPDATE attendance_records SET StudentID = ?, Date = ?, Period = ?, Course = ?, Type = ? WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getStudentID());
            statement.setDate(2, record.getDate());
            statement.setString(3, record.getPeriod());
            statement.setString(4, record.getCourse());
            statement.setString(5, record.getType());
            statement.setInt(6, record.getRecordID());
            statement.executeUpdate();
        }
    }

    public void deleteAttendanceRecord(int recordID) throws SQLException {
        String query = "DELETE FROM attendance_records WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordID);
            statement.executeUpdate();
        }
    }

    public AttendanceRecord getAttendanceRecordByID(int recordID) throws SQLException {
        String query = "SELECT * FROM attendance_records WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createAttendanceRecordFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<AttendanceRecord> getAttendanceRecordsByStudentID(int studentID) throws SQLException {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT * FROM attendance_records WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    AttendanceRecord record = createAttendanceRecordFromResultSet(resultSet);
                    records.add(record);
                }
            }
        }
        return records;
    }

    public List<AttendanceRecord> getAllAttendanceRecords() throws SQLException {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT * FROM attendance_records";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                AttendanceRecord record = createAttendanceRecordFromResultSet(resultSet);
                records.add(record);
            }
        }
        return records;
    }

    private AttendanceRecord createAttendanceRecordFromResultSet(ResultSet resultSet) throws SQLException {
        int recordID = resultSet.getInt("RecordID");
        int studentID = resultSet.getInt("StudentID");
        Date date = resultSet.getDate("Date");
        String period = resultSet.getString("Period");
        String course = resultSet.getString("Course");
        String type = resultSet.getString("Type");
        return new AttendanceRecord(recordID, studentID, date, period, course, type);
    }
}
