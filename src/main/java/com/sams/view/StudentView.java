package com.sams.view;

import com.sams.dao.StudentDAO;
import com.sams.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class StudentView {
    private Stage stage;
    private ListView<Student> studentListView;
    private ObservableList<Student> studentList;

    public StudentView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        studentList = FXCollections.observableArrayList();
        studentListView = new ListView<>(studentList);

        Button refreshButton = new Button("刷新");
        refreshButton.setOnAction(e -> {
            updateStudentList();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(studentListView, refreshButton);

        Scene scene = new Scene(layout, 300, 400);
        stage.setScene(scene);
        stage.setTitle("学生列表");
    }

    public void show() {
        updateStudentList();
        stage.showAndWait();
    }

    private void updateStudentList() throws SQLException {
        studentList.clear();
        StudentDAO studentDAO = new StudentDAO();
        studentList.addAll(studentDAO.getAllStudents());
    }
}
