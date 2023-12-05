package com.sams.ui;

import com.sams.model.Student;
import com.sams.service.StudentService;
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
public class StudentManagementUI extends VBox {
    private StudentService studentService;
    private TableView<Student> studentTable;
    private TextField studentIdField;
    private TextField nameField;
    private TextField genderField;
    private TextField ageField;
    private TextField studentClassField;
    public StudentManagementUI(StudentService studentService) throws SQLException {
        this.studentService = studentService;
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.TOP_CENTER);
        Label titleLabel = new Label("学生管理");
        titleLabel.setFont(Font.font(18));
        studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Student, String> studentIdColumn = new TableColumn<>("学号");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentID()));
        TableColumn<Student, String> nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        TableColumn<Student, String> genderColumn = new TableColumn<>("性别");
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        TableColumn<Student, String> ageColumn = new TableColumn<>("年龄");
        ageColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAge()).asString());
        TableColumn<Student, String> studentClassColumn = new TableColumn<>("班级");
        studentClassColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentClass()));
        studentTable.getColumns().addAll(studentIdColumn, nameColumn, genderColumn, ageColumn, studentClassColumn);
        Label studentIdLabel = new Label("学号:");
        Label nameLabel = new Label("姓名:");
        Label genderLabel = new Label("性别:");
        Label ageLabel = new Label("年龄:");
        Label studentClassLabel = new Label("班级:");
        studentIdField = new TextField();
        nameField = new TextField();
        genderField = new TextField();
        ageField = new TextField();
        studentClassField = new TextField();
        HBox formLayout = new HBox(10);
        formLayout.getChildren().addAll(studentIdLabel, studentIdField, nameLabel, nameField,
                genderLabel, genderField, ageLabel, ageField, studentClassLabel, studentClassField);

        Button searchButton = new Button("查询学生");
        searchButton.setOnAction(e -> {
            try {
                handleSearchButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button addButton = new Button("添加学生");
        addButton.setOnAction(e -> {
            try {
                handleAddButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        Button deleteButton = new Button("删除学生");
        deleteButton.setOnAction(e -> {
            try {
                handleDeleteButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(addButton, deleteButton, searchButton);
        refreshStudentTable();
        getChildren().addAll(titleLabel, studentTable, formLayout, buttonLayout);
    }
    private void refreshStudentTable() throws SQLException {
        ObservableList<Student> students = FXCollections.observableArrayList(studentService.getAllStudents());
        studentTable.setItems(students);
    }
    private void handleAddButton() throws SQLException {
        String studentId = studentIdField.getText();
        String name = nameField.getText();
        String gender = genderField.getText();
        int age = Integer.parseInt(ageField.getText());
        String studentClass = studentClassField.getText();
        Student student = new Student(studentId, name, gender, age, studentClass);
        studentService.addStudent(student);
        clearFormFields();
        refreshStudentTable();
    }
    private void handleSearchButton() throws SQLException {
        String searchText = studentIdField.getText();
        // 根据学号或者姓名进行查询
        Student searchedStudent = studentService.getStudentByID(searchText);
        if (searchedStudent == null) {
            // 没有找到匹配的学生
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("查询结果");
            alert.setHeaderText(null);
            alert.setContentText("无该学生信息");
            alert.showAndWait();
        } else {
            // 显示匹配的学生信息
            studentTable.getSelectionModel().select(searchedStudent);
            studentIdField.setText(searchedStudent.getStudentID());
            nameField.setText(searchedStudent.getName());
            genderField.setText(searchedStudent.getGender());
            ageField.setText(String.valueOf(searchedStudent.getAge()));
            studentClassField.setText(searchedStudent.getStudentClass());
        }
    }
    private void handleDeleteButton() throws SQLException {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentService.deleteStudent(selectedStudent.getStudentID());
            clearFormFields();
            refreshStudentTable();
        }
    }
    private void clearFormFields() {
        studentIdField.clear();
        nameField.clear();
        genderField.clear();
        ageField.clear();
        studentClassField.clear();
    }
}