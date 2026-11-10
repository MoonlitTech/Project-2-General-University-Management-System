package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.UniversityManager;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class ProjectDissertationTab extends Tab {

    private UniversityManager manager;

    private TextField studentId;
    private TextField projectName;
    private TextField dissertationName;

    public ProjectDissertationTab(UniversityManager manager) {

        this.manager = manager;

        setText("Projects / Dissertations");

        VBox layout = new VBox(18);
        layout.setPadding(new Insets(25));

        Label heading = new Label("Projects and Dissertations");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        studentId = new TextField();
        studentId.setPromptText("Enter student ID");

        // MS project
        projectName = new TextField();
        projectName.setPromptText("Enter MS project title");

        Button projectButton = new Button("Register MS Project");

        projectButton.setOnAction(event -> registerProject());

        // PhD dissertation
        dissertationName = new TextField();
        dissertationName.setPromptText("Enter PhD dissertation title");

        Button dissertationButton =
                new Button("Register PhD Dissertation");

        dissertationButton.setOnAction(
                event -> registerDissertation()
        );

        layout.getChildren().addAll(
                heading,
                new Label("Student ID:"),
                studentId,

                new Separator(),

                new Label("MS Project"),
                projectName,
                projectButton,

                new Separator(),

                new Label("PhD Dissertation"),
                dissertationName,
                dissertationButton
        );

        setContent(layout);
    }

    private void registerProject() {

        String id = studentId.getText().trim();
        String project = projectName.getText().trim();

        if (id.isEmpty() || project.isEmpty()) {

            showError("Please enter a student ID and project title.");
            return;
        }

        // Ask the manager to register the project
        String error = manager.registerProject(id, project);

        if (error != null) {

            showError(error);

        } else {

            showSuccess("MS project successfully registered!");
            projectName.clear();
        }
    }

    private void registerDissertation() {

        String id = studentId.getText().trim();
        String dissertation = dissertationName.getText().trim();

        if (id.isEmpty() || dissertation.isEmpty()) {

            showError("Please enter a student ID and dissertation title.");
            return;
        }

        // Ask the manager to register the dissertation
        String error = manager.registerDissertation(id, dissertation);

        if (error != null) {

            showError(error);

        } else {

            showSuccess("PhD dissertation successfully registered!");
            dissertationName.clear();
        }
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Registration Error");
        alert.setHeaderText("Unable to Register");
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