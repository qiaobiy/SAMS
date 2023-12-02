package com.sams.model;

import java.util.Date;

public class AttendanceRecord {

    public AttendanceRecord() {
        // 无参构造函数
    }
    private int recordID;
    private String studentID;
    private java.sql.Date date;
    private String period;
    private String course;
    private String type;

    public AttendanceRecord(int recordID, String studentID, java.sql.Date date, String period, String course, String type) {
        this.recordID = recordID;
        this.studentID = studentID;
        this.date = date;
        this.period = period;
        this.course = course;
        this.type = type;
    }


    // Getters and setters

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
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
