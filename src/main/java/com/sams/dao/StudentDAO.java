package com.sams.dao;

import com.sams.model.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO studenttable (StudentID, Name, Gender, Age, StudentClass) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getStudentClass());
            statement.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE studenttable SET Name = ?, Gender = ?, Age = ?, StudentClass = ? WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getGender());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getStudentClass());
            statement.setString(5, student.getStudentID());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(String studentID) throws SQLException {
        String query = "DELETE FROM studenttable WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentID);
            statement.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM studenttable";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String studentID = resultSet.getString("StudentID");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                int age = resultSet.getInt("Age");
                String studentClass = resultSet.getString("StudentClass");
                Student student = new Student(studentID, name, gender, age, studentClass);
                students.add(student);
            }
        }
        return students;
    }

    public Student getStudentByID(String studentID) throws SQLException {
        String query = "SELECT * FROM studenttable WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, studentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String gender = resultSet.getString("Gender");
                    int age = resultSet.getInt("Age");
                    String studentClass = resultSet.getString("StudentClass");
                    return new Student(studentID, name, gender, age, studentClass);
                }
            }
        }
        return null;
    }
}
