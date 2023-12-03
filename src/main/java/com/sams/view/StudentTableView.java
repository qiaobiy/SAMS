// com.sams.view.StudentTableView.java

package com.sams.view;

import com.sams.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentTableView {

    private TableView<Student> table = new TableView<>();

    public StudentTableView() {
        TableColumn<Student, String> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Student, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Student, Integer> ageColumn = new TableColumn<>("Age");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Student, String> classColumn = new TableColumn<>("Class");
        classColumn.setCellValueFactory(new PropertyValueFactory<>("class"));

        table.getColumns().add(studentIdColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(genderColumn);
        table.getColumns().add(ageColumn);
        table.getColumns().add(classColumn);

        // Load data from database
        ObservableList<Student> data = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sams", "root", "142318");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            while (resultSet.next()) {
                String studentID = resultSet.getString("StudentID");
                String name = resultSet.getString("Name");
                String gender = resultSet.getString("Gender");
                int age = resultSet.getInt("Age");
                String studetclass = resultSet.getString("StudentClass");

                data.add(new Student(studentID, name, gender, age, studetclass));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setItems(data);
    }

    public TableView<Student> getTable() {
        return table;
    }
}
