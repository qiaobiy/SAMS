package com.sams.model;

import java.util.Date;

public class AttendanceRecord {
    private int recordID;
    private int studentID;
    private Date date;
    private String period;
    private String course;
    private String type;

    public AttendanceRecord(int recordID, int studentID, Date date, String period, String course, String type) {
        this.recordID = recordID;
        this.studentID = studentID;
        this.date = date;
        this.period = String.valueOf(period);
        this.course = course;
        this.type = type;
    }

    public int getRecordID() {
        return recordID;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = String.valueOf(period);
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

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "recordID=" + recordID +
                ", studentID=" + studentID +
                ", date='" + date + '\'' +
                ", period=" + period +
                ", course='" + course + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
