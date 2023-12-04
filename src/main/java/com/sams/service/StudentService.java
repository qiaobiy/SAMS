package com.sams.service;

import com.sams.dao.StudentDAO;
import com.sams.model.Student;
import com.sams.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private Connection  connection = DatabaseUtil.getConnection();


    private StudentDAO studentDAO;

    public StudentService(Connection connection) throws SQLException {
        studentDAO = new StudentDAO(connection);
    }

    public void addStudent(Student student) throws SQLException {
        studentDAO.addStudent(student);
    }

    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    public void deleteStudent(String studentID) throws SQLException {
        studentDAO.deleteStudent(studentID);
    }

    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    public Student getStudentByID(String studentID) throws SQLException {
        return studentDAO.getStudentByID(studentID);
    }


}
