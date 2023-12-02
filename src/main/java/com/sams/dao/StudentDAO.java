package com.sams.dao;

import com.sams.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private Connection connection;

    public StudentDAO(Connection connection) {
        this.connection = connection;
    }

    public StudentDAO() {

    }

    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO students (StudentID, Name, Gender, Age, Class) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setString(3, student.getGender());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getClassName());
            statement.executeUpdate();
        }
    }

    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE students SET Name = ?, Gender = ?, Age = ?, Class = ? WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getGender());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getClassName());
            statement.setInt(5, student.getStudentID());
            statement.executeUpdate();
        }
    }

    public void deleteStudent(int studentID) throws SQLException {
        String query = "DELETE FROM students WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentID);
            statement.executeUpdate();
        }
    }

    public Student getStudentByID(int studentID) throws SQLException {
        String query = "SELECT * FROM students WHERE StudentID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, studentID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return createStudentFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Student student = createStudentFromResultSet(resultSet);
                students.add(student);
            }
        }
        return students;
    }

    private Student createStudentFromResultSet(ResultSet resultSet) throws SQLException {
        int studentID = resultSet.getInt("StudentID");
        String name = resultSet.getString("Name");
        String gender = resultSet.getString("Gender");
        int age = resultSet.getInt("Age");
        String studentClass = resultSet.getString("Class");
        return new Student(studentID, name, gender, age, studentClass);
    }
}
