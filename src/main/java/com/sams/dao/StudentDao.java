// com.sams.dao.StudentDao.java

package com.sams.dao;

import com.sams.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private static final String URL = "jdbc:mysql://localhost:3306/sams";
    private static final String USER = "root";
    private static final String PASSWORD = "142318";

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // 增加学生
    public void addStudent(Student student) throws Exception {
        String sql = "INSERT INTO studenttable (Name, StudentID, Gender, Age, Class) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getStudentID());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setString(5, student.getClassName());
            preparedStatement.executeUpdate();
        }
    }

    // 删除学生
    public void deleteStudent(String studentID) throws Exception {
        String sql = "DELETE FROM studenttable WHERE StudentID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentID);
            preparedStatement.executeUpdate();
        }
    }

    // 更新学生
    public void updateStudent(Student student) throws Exception {
        String sql = "UPDATE studenttable SET Name = ?, Gender = ?, Age = ?, Class = ? WHERE StudentID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setString(4, student.getClassName());
            preparedStatement.setString(5, student.getStudentID());
            preparedStatement.executeUpdate();
        }
    }

    // 查询学生
    public List<Student> getStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM studenttable";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getString("Name"),
                        resultSet.getString("StudentID"),
                        resultSet.getString("Gender"),
                        resultSet.getInt("Age"),
                        resultSet.getString("Class")
                );
                students.add(student);
            }
        }
        return students;
    }
}
