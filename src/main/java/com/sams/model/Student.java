package com.sams.model;

public class Student {
    private String name;
    private String studentID;
    private String gender;
    private int age;
    private String className;

    public Student(String name, String studentID, String gender, int age, String className) {
        this.name = name;
        this.studentID = studentID;
        this.gender = gender;
        this.age = age;
        this.className = className;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
