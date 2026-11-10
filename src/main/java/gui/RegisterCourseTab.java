package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.*;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class RegisterCourseTab extends Tab {

    private UniversityManager manager;

    private TextField studentId;
    private ComboBox<Course> courseList;
    private TextArea result;

    public RegisterCourseTab(UniversityManager manager) {

        this.manager = manager;

        setText("Register Course");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));

        Label heading = new Label("Register for a Course");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        studentId = new TextField();
        studentId.setPromptText("Enter student ID");

        // Load available courses
        courseList = new ComboBox<>();

        courseList.setItems(
                FXCollections.observableArrayList(
                        manager.getAvailableCourses()
                )
        );

        courseList.setPromptText("Select a course");
        courseList.setMaxWidth(Double.MAX_VALUE);

        Button registerButton = new Button("Register Course");

        registerButton.setOnAction(event -> registerCourse());

        result = new TextArea();
        result.setEditable(false);
        result.setPrefRowCount(10);

        result.setPromptText("Registration results will appear here.");

        layout.getChildren().addAll(
                heading,
                new Label("Student ID:"),
                studentId,
                new Label("Available Courses:"),
                courseList,
                registerButton,
                new Label("Registration Results:"),
                result
        );

        setContent(layout);
    }

    private void registerCourse() {

        String id = studentId.getText().trim();
        Course selectedCourse = courseList.getValue();

        if (id.isEmpty() || selectedCourse == null) {

            showError("Please enter a student ID and select a course.");
            return;
        }

        Student student = manager.findStudent(id);

        if (student == null) {

            showError("Student ID not found.");
            return;
        }

        // Register using the course code
        String error = manager.registerCourse(
                id,
                selectedCourse.getCode()
        );

        if (error != null) {

            showError(error);
            return;
        }

        // Display student information
        StringBuilder information = new StringBuilder();

        information.append("Last Name: ")
                .append(student.getLastName()).append("\n");

        information.append("First Name: ")
                .append(student.getFirstName()).append("\n");

        information.append("Student ID: ")
                .append(student.getId()).append("\n\n");

        information.append("Registered Courses:\n");

        for (Course course : student.getRegisteredCourses()) {

            information.append(course.getCode())
                    .append(" - ")
                    .append(course.getTitle())
                    .append(" (")
                    .append(course.getCredits())
                    .append(" credits)\n");
        }

        result.setText(information.toString());

        showSuccess("Course successfully registered!");
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Registration Error");
        alert.setHeaderText("Unable to Register Course");
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