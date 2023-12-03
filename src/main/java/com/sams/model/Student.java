package com.sams.model;

public class Student {
    private String studentID;
    private String name;
    private String gender;
    private int age;
    private String studentClass;

    public Student(String studentID, String name, String gender, int age, String studentClass) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.studentClass = studentClass;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

}
