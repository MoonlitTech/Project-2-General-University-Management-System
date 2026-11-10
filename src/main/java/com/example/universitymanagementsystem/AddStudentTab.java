package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.*;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddStudentTab extends Tab {

    private UniversityManager manager;

    private TextField lastName;
    private TextField firstName;
    private TextField phone;
    private TextField studentId;
    private TextField address;

    private ComboBox<String> studentType;

    public AddStudentTab(UniversityManager manager) {

        this.manager = manager;

        setText("Add Student");

        VBox layout = new VBox(20);
        layout.setPadding(new Insets(30));

        Label heading = new Label("Add a New Student");
        heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Create input fields
        lastName = new TextField();
        firstName = new TextField();
        phone = new TextField();
        studentId = new TextField();
        address = new TextField();

        lastName.setPromptText("Enter last name");
        firstName.setPromptText("Enter first name");
        phone.setPromptText("Enter phone number");
        studentId.setPromptText("Enter student ID");
        address.setPromptText("Enter address");

        // Student type dropdown
        studentType = new ComboBox<>();

        studentType.getItems().addAll(
                "Undergraduate",
                "MS",
                "PhD"
        );

        studentType.setPromptText("Select student type");
        studentType.setMaxWidth(Double.MAX_VALUE);

        // Organize the input fields
        GridPane form = new GridPane();

        form.setHgap(15);
        form.setVgap(15);

        form.add(new Label("Last Name:"), 0, 0);
        form.add(lastName, 1, 0);

        form.add(new Label("First Name:"), 0, 1);
        form.add(firstName, 1, 1);

        form.add(new Label("Phone Number:"), 0, 2);
        form.add(phone, 1, 2);

        form.add(new Label("Student ID:"), 0, 3);
        form.add(studentId, 1, 3);

        form.add(new Label("Address:"), 0, 4);
        form.add(address, 1, 4);

        form.add(new Label("Student Type:"), 0, 5);
        form.add(studentType, 1, 5);

        // Buttons
        Button addButton = new Button("Add Student");
        Button clearButton = new Button("Clear");

        addButton.setOnAction(event -> addStudent());
        clearButton.setOnAction(event -> clearFields());

        HBox buttons = new HBox(10, addButton, clearButton);

        layout.getChildren().addAll(
                heading,
                form,
                buttons
        );

        setContent(layout);
    }

    private void addStudent() {

        String last = lastName.getText().trim();
        String first = firstName.getText().trim();
        String phoneNumber = phone.getText().trim();
        String id = studentId.getText().trim();
        String studentAddress = address.getText().trim();

        String type = studentType.getValue();

        // Check for blank fields
        if (last.isEmpty() || first.isEmpty()
                || phoneNumber.isEmpty() || id.isEmpty()
                || studentAddress.isEmpty() || type == null) {

            showError("Please complete all student fields.");
            return;
        }

        // Create the correct student type
        Student student;

        if (type.equals("Undergraduate")) {

            student = new UndergraduateStudent(
                    last, first, phoneNumber, id, studentAddress
            );

        } else if (type.equals("MS")) {

            student = new MSStudent(
                    last, first, phoneNumber, id, studentAddress
            );

        } else {

            student = new PhDStudent(
                    last, first, phoneNumber, id, studentAddress
            );
        }

        // Add the student to the university
        boolean added = manager.addStudent(student);

        if (added) {

            showSuccess("Student successfully added!");
            clearFields();

        } else {

            showError("Student could not be added. Check whether the student ID already exists.");
        }
    }

    private void clearFields() {

        lastName.clear();
        firstName.clear();
        phone.clear();
        studentId.clear();
        address.clear();

        studentType.setValue(null);
    }

    private void showError(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Error");
        alert.setHeaderText("Unable to Add Student");
        alert.setContentText(message);

        alert.showAndWait();
    }

    private void showSuccess(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Success");
        alert.setHeaderText("Student Added");
        alert.setContentText(message);

        alert.showAndWait();
    }
}