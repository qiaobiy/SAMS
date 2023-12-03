// com.sams.model.Student.java

package com.sams.model;

public class Student {
    private String name;
    private String studentID;
    private String gender;
    private int age;
    private String className;

    // 构造方法，getters 和 setters
    public Student(String name, String studentID, String gender, int age, String className) {
        this.name = name;
        this.studentID = studentID;
        this.gender = gender;
        this.age = age;
        this.className = className;
    }

    // getters
    public String getName() { return name; }
    public String getStudentID() { return studentID; }
    public String getGender() { return gender; }
    public int getAge() { return age; }
    public String getClassName() { return className; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setStudentID(String studentID) { this.studentID = studentID; }
    public void setGender(String gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setClassName(String className) { this.className = className; }
}
