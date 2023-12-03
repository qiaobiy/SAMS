// com.sams.controller.StudentController.java

package com.sams.controller;

import com.sams.dao.StudentDao;
import com.sams.model.Student;
import java.util.List;

public class StudentController {
    private StudentDao studentDao;

    public StudentController() {
        studentDao = new StudentDao();
    }

    // 添加学生
    public void addStudent(Student student) throws Exception {
        studentDao.addStudent(student);
    }

    // 删除学生
    public void deleteStudent(String studentID) throws Exception {
        studentDao.deleteStudent(studentID);
    }

    // 更新学生
    public void updateStudent(Student student) throws Exception {
        studentDao.updateStudent(student);
    }

    // 获取学生列表
    public List<Student> getStudents() throws Exception {
        return studentDao.getStudents();
    }
}
