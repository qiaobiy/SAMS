// com.sams.controller.AttendanceRecordController.java

package com.sams.controller;

import com.sams.dao.AttendanceRecordDao;
import com.sams.model.AttendanceRecord;
import java.util.List;

public class AttendanceRecordController {
    private AttendanceRecordDao attendanceRecordDao;

    public AttendanceRecordController() {
        attendanceRecordDao = new AttendanceRecordDao();
    }

    // 添加出勤记录
    public void addRecord(AttendanceRecord record) throws Exception {
        attendanceRecordDao.addRecord(record);
    }

    // 删除出勤记录
    public void deleteRecord(int recordID) throws Exception {
        attendanceRecordDao.deleteRecord(recordID);
    }

    // 更新出勤记录
    public void updateRecord(AttendanceRecord record) throws Exception {
        attendanceRecordDao.updateRecord(record);
    }

    // 获取出勤记录列表
    public List<AttendanceRecord> getRecords() throws Exception {
        return attendanceRecordDao.getRecords();
    }
}
