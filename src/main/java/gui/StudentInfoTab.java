package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.*;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class StudentInfoTab extends Tab {

    private UniversityManager manager;

    private TextField studentId;
    private TextArea information;
    private ComboBox<Course> courseList;
    private TextField gradeField;

    public StudentInfoTab(UniversityManager manager) {

        this.manager = manager;

        setText("Student Info");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));

        Label heading = new Label("Student Information");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        studentId = new TextField();
        studentId.setPromptText("Enter student ID");

        Button searchButton = new Button("Search Student");

        searchButton.setOnAction(event -> searchStudent());

        information = new TextArea();
        information.setEditable(false);
        information.setPrefRowCount(10);

        information.setPromptText(
                "Student information, grades, and GPA will appear here."
        );

        // Load available courses
        courseList = new ComboBox<>();

        courseList.getItems().addAll(
                manager.getAvailableCourses()
        );

        courseList.setPromptText("Select a course");
        courseList.setMaxWidth(Double.MAX_VALUE);

        gradeField = new TextField();
        gradeField.setPromptText("Enter grade (0-100)");

        Button gradeButton = new Button("Set Grade");

        gradeButton.setOnAction(event -> setGrade());

        layout.getChildren().addAll(
                heading,
                new Label("Student ID:"),
                studentId,
                searchButton,
                new Label("Student Details, Grades, and GPA:"),
                information,
                new Label("Assign a Grade:"),
                courseList,
                gradeField,
                gradeButton
        );

        setContent(layout);
    }

    private void searchStudent() {

        String id = studentId.getText().trim();

        if (id.isEmpty()) {

            showError("Please enter a student ID.");
            return;
        }

        Student student = manager.findStudent(id);

        if (student == null) {

            showError("Student ID not found.");
            return;
        }

        // Display the student's information
        information.setText(student.getInfo());
    }

    private void setGrade() {

        String id = studentId.getText().trim();

        if (id.isEmpty() || courseList.getValue() == null
                || gradeField.getText().trim().isEmpty()) {

            showError("Please complete all grade fields.");
            return;
        }

        Student student = manager.findStudent(id);

        if (student == null) {

            showError("Student ID not found.");
            return;
        }

        try {

            double grade = Double.parseDouble(
                    gradeField.getText().trim()
            );

            // Save the grade using the course code
            String error = manager.setGrade(
                    id,
                    courseList.getValue().getCode(),
                    grade
            );

            if (error != null) {

                showError(error);
                return;
            }

            // Refresh the information
            information.setText(student.getInfo());

            gradeField.clear();

            showSuccess("Grade successfully saved!");

        } catch (NumberFormatException e) {

            showError("Please enter a valid numeric grade.");
        }
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Student Information Error");
        alert.setHeaderText("Unable to Complete Request");
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showSuccess(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setContentText(message);

        alert.showAndWait();
    }
}