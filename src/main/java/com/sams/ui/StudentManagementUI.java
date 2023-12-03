package com.sams.ui;

import com.sams.model.Student;
import com.sams.service.StudentService;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
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

        Label titleLabel = new Label("Student Management");
        titleLabel.setFont(Font.font(18));

        // Student table
        studentTable = new TableView<>();
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Student, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentID()));
        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        TableColumn<Student, String> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getAge()).asString());
        TableColumn<Student, String> studentClassColumn = new TableColumn<>("Class");
        studentClassColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentClass()));
        studentTable.getColumns().addAll(studentIdColumn, nameColumn, genderColumn, ageColumn, studentClassColumn);



        // Student form
        Label studentIdLabel = new Label("Student ID:");
        Label nameLabel = new Label("Name:");
        Label genderLabel = new Label("Gender:");
        Label ageLabel = new Label("Age:");
        Label studentClassLabel = new Label("Class:");
        studentIdField = new TextField();
        nameField = new TextField();
        genderField = new TextField();
        ageField = new TextField();
        studentClassField = new TextField();

        HBox formLayout = new HBox(10);
        formLayout.getChildren().addAll(studentIdLabel, studentIdField, nameLabel, nameField,
                genderLabel, genderField, ageLabel, ageField, studentClassLabel, studentClassField);


//      Buttons
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> {
            try {
                handleSearchButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                handleAddButton();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button updateButton = new Button("Update");
        updateButton.setOnAction(e -> {
            try {
                handleUpdateButton();
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



        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(addButton, updateButton, deleteButton, searchButton);
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
            alert.setTitle("Search Result");
            alert.setHeaderText(null);
            alert.setContentText("No matching student found.");
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




    private void handleUpdateButton() throws SQLException {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String studentId = studentIdField.getText();
            String name = nameField.getText();
            String gender = genderField.getText();
            int age = Integer.parseInt(ageField.getText());
            String studentClass = studentClassField.getText();

            selectedStudent.setStudentID(studentId);
            selectedStudent.setName(name);
            selectedStudent.setGender(gender);
            selectedStudent.setAge(age);
            selectedStudent.setStudentClass(studentClass);

            studentService.updateStudent(selectedStudent);

            clearFormFields();
            refreshStudentTable();
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
