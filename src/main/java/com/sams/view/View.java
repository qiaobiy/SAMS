package com.sams.view;

import com.sams.controller.AttendanceRecordController;
import com.sams.controller.StudentController;
import com.sams.model.AttendanceRecord;
import com.sams.model.Student;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class View {
    private StudentController studentController;
    private AttendanceRecordController attendanceRecordController;

    public View() {
        studentController = new StudentController();
        attendanceRecordController = new AttendanceRecordController();
    }

    public void displayMenu() {
        System.out.println("欢迎使用学生考勤管理系统！");
        System.out.println("1. 查看所有学生信息");
        System.out.println("2. 查看所有考勤记录");
        System.out.println("3. 添加考勤记录");
        System.out.println("4. 退出系统");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            System.out.print("请输入选项：");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    displayAllAttendanceRecords();
                    break;
                case 3:
                    addAttendanceRecord();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("无效选项，请重新输入！");
            }
        }

        System.out.println("感谢使用学生考勤管理系统！");
    }

    private void displayAllStudents() {
        List<Student> students = studentController.getAllStudents();

        System.out.println("所有学生信息：");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void displayAllAttendanceRecords() {
        List<AttendanceRecord> attendanceRecords = attendanceRecordController.getAllAttendanceRecords();

        System.out.println("所有考勤记录：");
        for (AttendanceRecord record : attendanceRecords) {
            System.out.println(record);
        }
    }

    private void addAttendanceRecord() {
        Scanner scanner = new Scanner(System.in);

        int recordID = 1;
        System.out.println("请输入考勤记录信息：");
        System.out.print("学号：");
        String studentID = scanner.nextLine();
        System.out.print("日期（格式：MM-dd）：");
        String inputDate = scanner.nextLine();

        // 解析月份和日期
        String[] parts = inputDate.split("-");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);

        // 获取当前年份
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        // 使用java.util.Calendar类创建日期对象
        calendar.set(year, month - 1, day); // 月份需要减1，因为Calendar类的月份从0开始

        // 将日期对象转换为java.sql.Date类型
        Date date = new Date(calendar.getTimeInMillis());

        System.out.print("课节次：");
        String period = scanner.nextLine();
        System.out.print("课程名：");
        String course = scanner.nextLine();
        System.out.print("缺勤类型（请假、旷课、迟到、早退）：");
        String type = scanner.nextLine();

        // 调用控制器的方法添加考勤记录
        attendanceRecordController.addAttendanceRecord(recordID, studentID, date, period, course, type);

        System.out.println("考勤记录添加成功！");
    }
}
