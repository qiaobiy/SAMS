package com.sams.model;

public class AttendanceRecord {
    private int recordID;
    private String name;
    private String studentID;
    private String date;
    private String period;
    private String course;
    private String type;

    public AttendanceRecord(int recordID, String name, String studentID, String date, String period, String course, String type) {
        this.recordID = recordID;
        this.name = name;
        this.studentID = studentID;
        this.date = date;
        this.period = period;
        this.course = course;
        this.type = type;
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
