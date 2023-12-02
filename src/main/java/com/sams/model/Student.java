package com.sams.model;

public class Student {
    private int studentID;
    private String name;
    private String gender;
    private int age;
    private String className;

    public Student(int studentID, String name, String gender, int age, String className) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.className = className;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", className='" + className + '\'' +
                '}';
    }
}
