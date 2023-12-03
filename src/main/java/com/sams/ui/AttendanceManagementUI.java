package com.sams.ui;

import com.sams.model.AttendanceRecord;
import com.sams.service.AttendanceRecordService;
import javafx.beans.binding.Bindings;
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

        attendanceTable.getColumns().addAll(recordIDColumn, nameColumn, studentIdColumn, dateColumn, periodColumn, courseColumn, typeColumn);

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
        formLayout.getChildren().addAll(recordIDLable, recordIDField, nameLabel, nameField, studentIdLabel, studentIdField,
                dateLabel, dateField, periodLabel, periodField, courseLabel, courseField, typeLabel, typeField);

        // Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                handleAddButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            try {
                handleSearchButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            try {
                handleUpdateButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            try {
                handleDeleteButton();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton, deleteButton, searchButton, updateButton);

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

        AttendanceRecord attendanceRecord = new AttendanceRecord(recordID, name, studentId, date, period, course, type);
        attendanceService.addAttendanceRecord(attendanceRecord);

        clearFormFields();
        refreshAttendanceTable();
    }

    private void handleSearchButton() throws SQLException{

            String searchTerm1 = recordIDField.getText().trim();
            String searchTerm2 = nameField.getText().trim();
            ObservableList<AttendanceRecord> attendanceRecords = null;

            if (searchTerm1.isEmpty() && searchTerm2.isEmpty()) {
                attendanceRecords = FXCollections.observableArrayList(attendanceService.getAllAttendanceRecords());
            } else if (searchTerm1.isEmpty() && !searchTerm2.isEmpty()) {
                String name = searchTerm2;
                attendanceRecords = FXCollections.observableArrayList(attendanceService.getAttendanceRecordsByName(name));
            } else if (!searchTerm1.isEmpty() && searchTerm2.isEmpty()) {
                int recordID = Integer.parseInt(searchTerm1);
                attendanceRecords = FXCollections.observableArrayList(attendanceService.getAttendanceRecordByID(recordID));
            }

            attendanceTable.setItems(attendanceRecords);
            clearFormFields();
        }

        private void handleUpdateButton() throws SQLException {
            int recordID = Integer.parseInt(recordIDField.getText());
            String name = nameField.getText();
            String studentId = studentIdField.getText();
            String date = dateField.getText();
            String period = periodField.getText();
            String course = courseField.getText();
            String type = typeField.getText();

            AttendanceRecord attendanceRecord = new AttendanceRecord(recordID, name, studentId, date, period, course, type);
            attendanceService.updateAttendanceRecord(attendanceRecord);

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
            recordIDField.clear();
            nameField.clear();
            studentIdField.clear();
            dateField.clear();
            periodField.clear();
            courseField.clear();
            typeField.clear();
        }
    }
