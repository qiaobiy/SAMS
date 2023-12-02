package com.sams.service;


import com.sams.model.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private com.example.dao.StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new com.example.dao.StudentDAO();
    }

    // 添加学生
    public void addStudent(Student student) throws SQLException {
        studentDAO.addStudent(student);
    }

    // 获取所有学生
    public List<Student> getAllStudents() throws SQLException {
        return studentDAO.getAllStudents();
    }

    // 根据学生ID获取学生
    public Student getStudentById(int studentId) throws SQLException {
        return studentDAO.getStudentByID(studentId);
    }

    // 更新学生信息
    public void updateStudent(Student student) throws SQLException {
        studentDAO.updateStudent(student);
    }

    // 删除学生
    public void deleteStudent(int studentId) throws SQLException {
        studentDAO.deleteStudent(studentId);
    }
}
