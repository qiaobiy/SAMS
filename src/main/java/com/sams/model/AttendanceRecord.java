// com.sams.model.AttendanceRecord.java

package com.sams.model;

import java.sql.Date;

public class AttendanceRecord {
    private int recordID;
    private String studentID;
    private Date date;
    private String period;
    private String course;
    private String type;

    // 构造方法，getters 和 setters
    public AttendanceRecord(int recordID, String studentID, Date date, String period, String course, String type) {
        this.recordID = recordID;
        this.studentID = studentID;
        this.date = date;
        this.period = period;
        this.course = course;
        this.type = type;
    }

    // getters
    public int getRecordID() { return recordID; }
    public String getStudentID() { return studentID; }
    public Date getDate() { return date; }
    public String getPeriod() { return period; }
    public String getCourse() { return course; }
    public String getType() { return type; }

    // setters
    public void setRecordID(int recordID) { this.recordID = recordID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }
    public void setDate(Date date) { this.date = date; }
    public void setPeriod(String period) { this.period = period; }
    public void setCourse(String course) { this.course = course; }
    public void setType(String type) { this.type = type; }
}
