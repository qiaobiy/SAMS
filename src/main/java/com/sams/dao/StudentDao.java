package com.sams.dao;

import com.sams.model.Student;

import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private Connection connection;

    public StudentDao() {
        // 在构造方法中进行数据库连接的初始化
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sams", "root", "142318");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String studentID = resultSet.getString("StudentID");
                String gender = resultSet.getString("Gender");
                int age = resultSet.getInt("Age");
                String className = resultSet.getString("Class");

                Student student = new Student(name, studentID, gender, age, className);
                students.add(student);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // 其他数据库操作方法，如插入、更新、删除等
}
