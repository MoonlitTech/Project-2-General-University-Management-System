package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.*;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class CoursesTab extends Tab {

    private UniversityManager manager;

    private ListView<String> availableCourses;
    private ListView<String> registeredCourses;

    private TextField studentId;

    public CoursesTab(UniversityManager manager) {

        this.manager = manager;

        setText("Courses");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(25));

        Label heading = new Label("Course Information");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Display all available courses
        availableCourses = new ListView<>();

        for (Course course : manager.getAvailableCourses()) {

            availableCourses.getItems().add(
                    course.getCode() + " - "
                            + course.getTitle() + " ("
                            + course.getCredits() + " credits)"
            );
        }

        studentId = new TextField();
        studentId.setPromptText("Enter student ID");

        Button searchButton = new Button("View Registered Courses");

        searchButton.setOnAction(event -> searchCourses());

        registeredCourses = new ListView<>();

        layout.getChildren().addAll(
                heading,
                new Label("All Available Courses:"),
                availableCourses,
                new Label("Search by Student ID:"),
                studentId,
                searchButton,
                new Label("Student's Registered Courses:"),
                registeredCourses
        );

        setContent(layout);
    }

    private void searchCourses() {

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

        registeredCourses.getItems().clear();

        if (student.getRegisteredCourses().isEmpty()) {

            registeredCourses.getItems().add(
                    "This student has no registered courses."
            );

        } else {

            for (Course course : student.getRegisteredCourses()) {

                registeredCourses.getItems().add(
                        course.getCode() + " - "
                                + course.getTitle() + " ("
                                + course.getCredits() + " credits)"
                );
            }
        }
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Course Search Error");
        alert.setHeaderText("Unable to Find Courses");
        alert.setContentText(message);

        alert.showAndWait();
    }
}