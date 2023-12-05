package com.sams.ui;

import com.sams.model.AttendanceRecord;
import com.sams.model.Student;
import com.sams.service.AttendanceRecordService;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.sql.SQLException;
import java.util.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Label titleLabel = new Label("考勤管理");
        titleLabel.setFont(Font.font(18));

        // Attendance table
        attendanceTable = new TableView<>();
        attendanceTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<AttendanceRecord, Integer> recordIDColumn = new TableColumn<>("序号");
        recordIDColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRecordID()));

        TableColumn<AttendanceRecord, String> nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<AttendanceRecord, String> studentIdColumn = new TableColumn<>("学号");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentID()));

        TableColumn<AttendanceRecord, String> dateColumn = new TableColumn<>("日期");
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

        TableColumn<AttendanceRecord, String> periodColumn = new TableColumn<>("节次");
        periodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPeriod()));

        TableColumn<AttendanceRecord, String> courseColumn = new TableColumn<>("课程");
        courseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));

        TableColumn<AttendanceRecord, String> typeColumn = new TableColumn<>("类型");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        attendanceTable.getColumns().addAll(recordIDColumn, nameColumn, studentIdColumn, dateColumn, periodColumn, courseColumn, typeColumn);
        // Buttons
        Button addButton = new Button("添加新纪录");
        addButton.setOnAction(e -> showaddDialog()
        );
        Button deleteButton = new Button("删除记录");
        deleteButton.setOnAction(e -> {
            try {
                handleDeleteButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button searchButton = new Button("查询记录");
        searchButton.setOnAction(e -> showsearchDialog()
        );

        Button ststisticButton = new Button("考勤统计");
        ststisticButton.setOnAction(e ->{
            Stage stage = new Stage();
            stage.setTitle("统计结果");
            List<AttendanceRecord> allRecords;
            try {
                allRecords = getAllAttendanceRecords();
            } catch (SQLException a) {
                a.printStackTrace();
                return;
            }
            // 筛选出Type为"旷到"的考勤记录，并进行统计
            Map<String, Integer> statistics = countAbsentRecords(allRecords);
            // 创建柱状图
            CategoryAxis xAxis = new CategoryAxis();
            NumberAxis yAxis = new NumberAxis();
            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            XYChart.Series<String, Number> series = new XYChart.Series<>();

            for (Map.Entry<String, Integer> entry : statistics.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            barChart.getData().add(series);
            Scene scene = new Scene(barChart, 1200, 600);
            stage.setScene(scene);
            stage.show();
    });

        HBox buttonLayout = new HBox(10);
        buttonLayout.getChildren().addAll(addButton,deleteButton, searchButton,ststisticButton);

        getChildren().addAll(titleLabel, attendanceTable,  buttonLayout);
        refreshAttendanceTable();
    }

    private void handleDeleteButton() throws SQLException {
        AttendanceRecord selecteattendance = attendanceTable.getSelectionModel().getSelectedItem();
        if (selecteattendance != null) {
            attendanceService.deleteAttendanceRecord(selecteattendance.getRecordID());
            refreshAttendanceTable();
        }
    }

    private List<AttendanceRecord> getAllAttendanceRecords() throws SQLException {
        return attendanceService.getAllAttendanceRecords();
    }

    private Map<String, Integer> countAbsentRecords(List<AttendanceRecord> records) {
        Map<String, Integer> statistics = new HashMap<>();

        for (AttendanceRecord record : records) {
            if (record.getType().equals("旷课")) {
                String studentID = record.getStudentID();
                statistics.put(studentID, statistics.getOrDefault(studentID, 0) + 1);
            }
        }
        return statistics;
    }
    private void showaddDialog() {
        // TODO Auto-generated method stub
        Dialog<AttendanceRecord> dialog = new Dialog<>();
        dialog.setTitle("添加新纪录");
        dialog.setHeaderText("请填写新纪录信息");
        GridPane grid = new GridPane();
        recordIDField = new TextField();
        nameField = new TextField();
        studentIdField = new TextField();
        dateField = new TextField();
        periodField = new TextField();
        courseField = new TextField();
        typeField = new TextField();
        grid.add(new Label("序号:"), 1, 1);
        grid.add(recordIDField, 2, 1);
        grid.add(new Label("姓名:"), 1, 2);
        grid.add(nameField, 2, 2);
        grid.add(new Label("学号:"), 1, 3);
        grid.add(studentIdField, 2, 3);
        grid.add(new Label("日期:"), 1, 4);
        grid.add(dateField, 2, 4);
        grid.add(new Label("节次:"), 1, 5);
        grid.add(periodField, 2, 5);
        grid.add(new Label("课程:"), 1, 6);
        grid.add(courseField, 2, 6);
        grid.add(new Label("类型:"), 1, 7);
        grid.add(typeField, 2, 7);

        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("添加", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(dialogButon ->{
            if (dialogButon == buttonTypeOk) {
                int recordID = Integer.parseInt(recordIDField.getText());
                String name = nameField.getText();
                String studentId = studentIdField.getText();
                String date = dateField.getText();
                String period = periodField.getText();
                String course = courseField.getText();
                String type = typeField.getText();
                AttendanceRecord attendanceRecord = new AttendanceRecord(recordID, name, studentId, date, period, course, type);
                clearFormFields();
                try {
                    refreshAttendanceTable();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                return attendanceRecord;
            }
            return null;
        });
        Optional<AttendanceRecord> result = dialog.showAndWait();
        result.ifPresent(attendanceRecord -> {
            try {
                attendanceService.addAttendanceRecord(attendanceRecord);
                refreshAttendanceTable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void showsearchDialog() {
        Dialog<AttendanceRecord> dialog = new Dialog<>();
        dialog.setTitle("查询记录");
        dialog.setHeaderText("请输入查询信息");
        GridPane grid = new GridPane();
        recordIDField = new TextField();
        grid.add(new Label("请输入记录序号:"), 1, 1);
        grid.add(recordIDField, 2, 1);

        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("查询", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);

        dialog.setResultConverter(dialogButon -> {
            if (dialogButon == buttonTypeOk) {
                int recordID = Integer.parseInt(recordIDField.getText());

                try {
                    AttendanceRecord searchedAttendanceRecord = attendanceService.getAttendanceRecordByID(recordID);
                    Stage stage = new Stage();
                    VBox vBox = new VBox();
                    Button fixButton = new Button("修改记录");
                    fixButton.setOnAction(e -> {
                        Dialog<AttendanceRecord> dialog1 = new Dialog<>();
                        dialog1.setTitle("修改记录");
                        dialog1.setHeaderText("请修改要更新的信息");
                        GridPane grid1 = new GridPane();
                        recordIDField = new TextField();
                        recordIDField.setText(String.valueOf(searchedAttendanceRecord.getRecordID()));
                        nameField = new TextField();
                        nameField.setText(searchedAttendanceRecord.getName());
                        studentIdField = new TextField();
                        studentIdField.setText(searchedAttendanceRecord.getStudentID());
                        dateField = new TextField();
                        dateField.setText(searchedAttendanceRecord.getDate());
                        periodField = new TextField();
                        periodField.setText(searchedAttendanceRecord.getPeriod());
                        courseField = new TextField();
                        courseField.setText(searchedAttendanceRecord.getCourse());
                        typeField = new TextField();
                        typeField.setText(searchedAttendanceRecord.getType());
                        grid1.add(new Label("序号:"), 1, 1);
                        grid1.add(recordIDField, 2, 1);
                        grid1.add(new Label("姓名:"), 1, 2);
                        grid1.add(nameField, 2, 2);
                        grid1.add(new Label("学号:"), 1, 3);
                        grid1.add(studentIdField, 2, 3);
                        grid1.add(new Label("日期:"), 1, 4);
                        grid1.add(dateField, 2, 4);
                        grid1.add(new Label("节次:"), 1, 5);
                        grid1.add(periodField, 2, 5);
                        grid1.add(new Label("课程:"), 1, 6);
                        grid1.add(courseField, 2, 6);
                        grid1.add(new Label("类型:"), 1, 7);
                        grid1.add(typeField, 2, 7);

                        dialog1.getDialogPane().setContent(grid1);
                        ButtonType buttonTypeOk1 = new ButtonType("修改", ButtonBar.ButtonData.OK_DONE);
                        dialog1.getDialogPane().getButtonTypes().add(buttonTypeOk1);
                        dialog1.setResultConverter(dialogButon1 ->{
                            if (dialogButon1 == buttonTypeOk1) {
                                int recordID1 = Integer.parseInt(recordIDField.getText());
                                String name1 = nameField.getText();
                                String studentId1 = studentIdField.getText();
                                String date1 = dateField.getText();
                                String period1 = periodField.getText();
                                String course1 = courseField.getText();
                                String type1 = typeField.getText();
                                AttendanceRecord attendanceRecord1 = new AttendanceRecord(recordID1, name1, studentId1, date1, period1, course1, type1);
                                try {
                                    refreshAttendanceTable();
                                    stage.close();
                                }
                                catch (SQLException e1) {
                                    throw new RuntimeException(e1);
                                }

                                return attendanceRecord1;
                            }
                            return null;
                        });
                        Optional<AttendanceRecord> result1 = dialog1.showAndWait();
                        result1.ifPresent(attendanceRecord1 -> {
                            try {
                                attendanceService.updateAttendanceRecord(attendanceRecord1);
                                refreshAttendanceTable();
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                        });
                    });


                    Label label = new Label("查询结果");
                    label.setFont(Font.font(18));
                    vBox.getChildren().add(label);
                    TableView<AttendanceRecord> tableView = new TableView<>();
                    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                    TableColumn<AttendanceRecord, Integer> recordIDColumn = new TableColumn<>("序号");
                    recordIDColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getRecordID()));

                    TableColumn<AttendanceRecord, String> nameColumn = new TableColumn<>("姓名");
                    nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

                    TableColumn<AttendanceRecord, String> studentIdColumn = new TableColumn<>("学号");
                    studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentID()));

                    TableColumn<AttendanceRecord, String> dateColumn = new TableColumn<>("日期");
                    dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));

                    TableColumn<AttendanceRecord, String> periodColumn = new TableColumn<>("节次");
                    periodColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPeriod()));

                    TableColumn<AttendanceRecord, String> courseColumn = new TableColumn<>("课程");
                    courseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourse()));

                    TableColumn<AttendanceRecord, String> typeColumn = new TableColumn<>("类型");
                    typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
                    tableView.getColumns().addAll(recordIDColumn, nameColumn, studentIdColumn, dateColumn, periodColumn, courseColumn, typeColumn);
                    ObservableList<AttendanceRecord> attendanceRecords = FXCollections.observableArrayList(searchedAttendanceRecord);
                    tableView.setItems(attendanceRecords);
                    vBox.getChildren().addAll(tableView,fixButton);
                    Scene scene = new Scene(vBox, 800, 600);
                    stage.setScene(scene);
                    stage.setTitle("查询结果");
                    stage.show();
                    refreshAttendanceTable();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return null;
        });
        dialog.showAndWait();
    }
    private void refreshAttendanceTable() throws SQLException {
        ObservableList<AttendanceRecord> attendanceRecords = FXCollections.observableArrayList(attendanceService.getAllAttendanceRecords());
        attendanceTable.setItems(attendanceRecords);
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