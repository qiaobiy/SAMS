package com.sams.dao;

import com.sams.model.AttendanceRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private Connection connection;

    public AttendanceDAO(Connection connection) {
        this.connection = connection;
    }

    public void addAttendanceRecord(AttendanceRecord record) throws SQLException {
        String query = "INSERT INTO attendancerecords (RecordID, Name, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, record.getRecordID());
            statement.setString(2, record.getName());
            statement.setString(3, record.getStudentID());
            statement.setString(4, record.getDate());
            statement.setString(5, record.getPeriod());
            statement.setString(6, record.getCourse());
            statement.setString(7, record.getType());
            statement.executeUpdate();
        }
    }

    public void updateAttendanceRecord(AttendanceRecord record) throws SQLException {
        String query = "UPDATE attendancerecords SET Name = ?, StudentID = ?, Date = ?, Period = ?, Course = ?, Type = ? WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, record.getName());
            statement.setString(2, record.getStudentID());
            statement.setString(3, record.getDate());
            statement.setString(4, record.getPeriod());
            statement.setString(5, record.getCourse());
            statement.setString(6, record.getType());
            statement.setInt(7, record.getRecordID());
            statement.executeUpdate();
        }
    }

    public void deleteAttendanceRecord(int recordID) throws SQLException {
        String query = "DELETE FROM attendancerecords WHERE RecordID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, recordID);
            statement.executeUpdate();
        }
    }

    public List<AttendanceRecord> getAllAttendanceRecords() throws SQLException {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT * FROM attendancerecords";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int recordID = resultSet.getInt("RecordID");
                String name = resultSet.getString("Name");
                String studentID = resultSet.getString("StudentID");
                String date = resultSet.getString("Date");
                String period = resultSet.getString("Period");
                String course = resultSet.getString("Course");
                String type = resultSet.getString("Type");
                AttendanceRecord record = new AttendanceRecord(recordID, name, studentID, date, period, course, type);
                records.add(record);
            }
        }
        return records;
    }

    public AttendanceRecord getAttendanceRecordByID(int recordID) throws SQLException {
        String query = "SELECT * FROM attendancerecords WHERE RecordID = ?";
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
    public List<AttendanceRecord> getAttendanceRecordsByName(String name) throws SQLException {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT * FROM attendancerecords WHERE Name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int recordID = resultSet.getInt("RecordID");
                    String studentID = resultSet.getString("StudentID");
                    String date = resultSet.getString("Date");
                    String period = resultSet.getString("Period");
                    String course = resultSet.getString("Course");
                    String type = resultSet.getString("Type");
                    AttendanceRecord record = new AttendanceRecord(recordID, name, studentID, date, period, course, type);
                    records.add(record);
                }
            }
        }
        return records;
    }

}
