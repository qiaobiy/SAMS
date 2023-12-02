package com.sams.dao;

import com.sams.model.AttendanceRecord;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class AttendanceRecordDao {


    private Connection connection;

    public AttendanceRecordDao() {
        // 在构造方法中进行数据库连接的初始化
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sams", "root", "142318");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AttendanceRecord> getAllAttendanceRecords() {
        List<AttendanceRecord> records = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM AttendanceRecords");

            while (resultSet.next()) {
                int recordID = resultSet.getInt("RecordID");
                String studentID = resultSet.getString("StudentID");
                Date date = resultSet.getDate("Date");
                String period = resultSet.getString("Period");
                String course = resultSet.getString("Course");
                String type = resultSet.getString("Type");

                AttendanceRecord record = new AttendanceRecord(recordID, studentID, date, period, course, type);
                records.add(record);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    public void addAttendanceRecord(AttendanceRecord attendanceRecord) {
        String query = "INSERT INTO AttendanceRecords (RecordID, StudentID, Date, Period, Course, Type) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, attendanceRecord.getRecordID());
            preparedStatement.setString(2, attendanceRecord.getStudentID());
            preparedStatement.setDate(3, (java.sql.Date.valueOf(String.valueOf(attendanceRecord.getDate()))));
            preparedStatement.setString(4, attendanceRecord.getPeriod());
            preparedStatement.setString(5, attendanceRecord.getCourse());
            preparedStatement.setString(6, attendanceRecord.getType());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 其他数据库操作方法，如插入、更新、删除等
}
