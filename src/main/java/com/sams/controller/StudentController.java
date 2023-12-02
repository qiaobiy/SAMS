package com.sams.controller;

import com.sams.dao.StudentDao;
import com.sams.model.Student;

import java.util.List;

public class StudentController {
    private StudentDao studentDao;

    public StudentController() {
        studentDao = new StudentDao();
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    // 其他业务逻辑方法，如添加学生、删除学生等
}
