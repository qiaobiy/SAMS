package com.sams.ui;

import com.sams.model.AttendanceRecord;
import com.sams.service.AttendanceRecordService;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.sql.SQLException;



public class AttendanceManagementUI extends VBox {
    private AttendanceRecordService attendanceService;
    private TableView<AttendanceRecord> attendanceTable;
    private TextField recordIDField;
    private TextField nameField;
    private TextField studentIdField;
    private TextField dateField;
    private TextField periodField;
    private TextField courseField;
    private TextField typeField;

    public AttendanceManagementUI(AttendanceRecordService attendanceService) throws SQLException {
        this.attendanceService = attendanceService;

        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_CENTER);

        Label titleLabel = new Label("Attendance Management");
        titleLabel.setFont(Font.font(18));

        // Attendance table
        attendanceTable = new TableView<>();
        attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<AttendanceRecord, Integer> recordIDColumn = new TableColumn<>("ID");
        recordIDColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRecordID()));

        TableColumn<AttendanceRecord, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        TableColumn<AttendanceRecord, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentID()));
        TableColumn<AttendanceRecord, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        TableColumn<AttendanceRecord, String> periodColumn = new TableColumn<>("Period");
        periodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPeriod()));
        TableColumn<AttendanceRecord, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));
        TableColumn<AttendanceRecord, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

        attendanceTable.getColumns().addAll(recordIDColumn,nameColumn, studentIdColumn, dateColumn, periodColumn, courseColumn, typeColumn);

        // Attendance form
        Label recordIDLable = new Label("Record ID");
        Label nameLabel = new Label("Name:");
        Label studentIdLabel = new Label("Student ID:");
        Label dateLabel = new Label("Date:");
        Label periodLabel = new Label("Period:");
        Label courseLabel = new Label("Course:");
        Label typeLabel = new Label("Type:");
        recordIDField = new TextField();
        nameField = new TextField();
        studentIdField = new TextField();
        dateField = new TextField();
        periodField = new TextField();
        courseField = new TextField();
        typeField = new TextField();

        HBox formLayout = new HBox(10);
        formLayout.getChildren().addAll(recordIDLable,recordIDField,nameLabel, nameField, studentIdLabel, studentIdField,
                dateLabel, dateField, periodLabel, periodField, courseLabel, courseField, typeLabel, typeField);

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                handleAddButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            try {
                handleDeleteButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton, deleteButton);

        getChildren().addAll(titleLabel, attendanceTable, formLayout, buttonLayout);

        refreshAttendanceTable();
    }

    private void refreshAttendanceTable() throws SQLException {
        ObservableList<AttendanceRecord> attendanceRecords = FXCollections.observableArrayList(attendanceService.getAllAttendanceRecords());
        attendanceTable.setItems(attendanceRecords);
    }

    private void handleAddButton() throws SQLException {
        int recordID = Integer.parseInt(recordIDField.getText());
        String name = nameField.getText();
        String studentId = studentIdField.getText();
        String date = dateField.getText();
        String period = periodField.getText();
        String course = courseField.getText();
        String type = typeField.getText();

        AttendanceRecord attendanceRecord = new AttendanceRecord(recordID,name, studentId, date, period, course, type);
        attendanceService.addAttendanceRecord(attendanceRecord);

        clearFormFields();
        refreshAttendanceTable();
    }

    private void handleDeleteButton() throws SQLException {
        AttendanceRecord selectedRecord = attendanceTable.getSelectionModel().getSelectedItem();
        if (selectedRecord != null) {
            attendanceService.deleteAttendanceRecord(selectedRecord.getRecordID());

            clearFormFields();
            refreshAttendanceTable();
        }
    }

    private void clearFormFields() {
        nameField.clear();
        studentIdField.clear();
        dateField.clear();
        periodField.clear();
        courseField.clear();
        typeField.clear();
    }
}
