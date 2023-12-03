// com.sams.view.AttendanceTableView.java

package com.sams.view;

import com.sams.model.AttendanceRecord;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class AttendanceTableView extends Application {

    @Override
    public void start(Stage primaryStage) {
        TableView<AttendanceRecord> tableView = new TableView<>();

        // Create columns
        TableColumn<AttendanceRecord, Integer> recordIDColumn = new TableColumn<>("Record ID");
        recordIDColumn.setCellValueFactory(new PropertyValueFactory<>("recordID"));

        TableColumn<AttendanceRecord, String> studentIDColumn = new TableColumn<>("Student ID");
        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        TableColumn<AttendanceRecord, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<AttendanceRecord, String> periodColumn = new TableColumn<>("Period");
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("period"));

        TableColumn<AttendanceRecord, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        TableColumn<AttendanceRecord, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        // Add columns to table
        tableView.getColumns().add(recordIDColumn);
        tableView.getColumns().add(studentIDColumn);
        tableView.getColumns().add(dateColumn);
        tableView.getColumns().add(periodColumn);
        tableView.getColumns().add(courseColumn);
        tableView.getColumns().add(typeColumn);

        // Load data from database
        ObservableList<AttendanceRecord> data = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sams", "root", "142318");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM attendancerecords");

            while (resultSet.next()) {
                int recordID = resultSet.getInt("RecordID");
                String studentID = resultSet.getString("StudentID");
                Date date = resultSet.getDate("Date");
                String period = resultSet.getString("Period");
                String course = resultSet.getString("Course");
                String type = resultSet.getString("Type");

                data.add(new AttendanceRecord(recordID, studentID, date, period, course, type));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableView.setItems(data);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
