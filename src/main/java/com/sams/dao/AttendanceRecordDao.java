// com.sams.dao.AttendanceRecordDao.java

package com.sams.dao;

import com.sams.model.AttendanceRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRecordDao {
    private static final String URL = "jdbc:mysql://localhost:3306/sams";
    private static final String USER = "root";
    private static final String PASSWORD = "142318";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 增加出勤记录
    public void addRecord(AttendanceRecord record) throws Exception {
        String sql = "INSERT INTO attendancerecords (RecordID, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, record.getRecordID());
            preparedStatement.setString(2, record.getStudentID());
            preparedStatement.setDate(3, new java.sql.Date(record.getDate().getTime()));
            preparedStatement.setString(4, record.getPeriod());
            preparedStatement.setString(5, record.getCourse());
            preparedStatement.setString(6, record.getType());
            preparedStatement.executeUpdate();
        }
    }

    // 删除出勤记录
    public void deleteRecord(int recordID) throws Exception {
        String sql = "DELETE FROM attendancerecords WHERE RecordID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, recordID);
            preparedStatement.executeUpdate();
        }
    }

    // 更新出勤记录
    public void updateRecord(AttendanceRecord record) throws Exception {
        String sql = "UPDATE attendancerecords SET StudentID = ?, Date = ?, Period = ?, Course = ?, Type = ? WHERE RecordID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, record.getStudentID());
            preparedStatement.setDate(2, new java.sql.Date(record.getDate().getTime()));
            preparedStatement.setString(3, record.getPeriod());
            preparedStatement.setString(4, record.getCourse());
            preparedStatement.setString(5, record.getType());
            preparedStatement.setInt(6, record.getRecordID());
            preparedStatement.executeUpdate();
        }
    }

    // 查询出勤记录
    public List<AttendanceRecord> getRecords() throws Exception {
        List<AttendanceRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM attendancerecords";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                AttendanceRecord record = new AttendanceRecord(
                        resultSet.getInt("RecordID"),
                        resultSet.getString("StudentID"),
                        resultSet.getDate("Date"),
                        resultSet.getString("Period"),
                        resultSet.getString("Course"),
                        resultSet.getString("Type")
                );
                records.add(record);
            }
        }
        return records;
    }
}
