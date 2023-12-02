package com.sams.view;

import com.sams.dao.AttendanceRecordDAO;
import com.sams.model.AttendanceRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AttendanceRecordView {
    private Stage stage;
    private ListView<AttendanceRecord> attendanceListView;
    private ObservableList<AttendanceRecord> attendanceList;

    public AttendanceRecordView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        attendanceList = FXCollections.observableArrayList();
        attendanceListView = new ListView<>(attendanceList);

        Button refreshButton = new Button("刷新");
        refreshButton.setOnAction(e -> {
            try {
                updateAttendanceList();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(attendanceListView, refreshButton);

        Scene scene = new Scene(layout, 300, 400);
        stage.setScene(scene);
        stage.setTitle("考勤记录列表");
    }

    public void show() throws SQLException {
        updateAttendanceList();
        stage.showAndWait();
    }

    private void updateAttendanceList() throws SQLException {
        attendanceList.clear();
        AttendanceRecordDAO attendanceDAO = new AttendanceRecordDAO();
        attendanceList.addAll(attendanceDAO.getAllAttendanceRecords());
    }
}
