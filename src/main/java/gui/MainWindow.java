package com.example.universitymanagementsystem.gui;

import com.example.universitymanagementsystem.model.UniversityManager;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainWindow extends TabPane {

    public MainWindow(UniversityManager manager) {

        // Create the five screens
        Tab addStudent = new AddStudentTab(manager);
        Tab registerCourse = new RegisterCourseTab(manager);
        Tab courses = new CoursesTab(manager);
        Tab studentInfo = new StudentInfoTab(manager);
        Tab projects = new ProjectDissertationTab(manager);

        // Add screens to the main window
        getTabs().addAll(
                addStudent,
                registerCourse,
                courses,
                studentInfo,
                projects
        );

        // Prevent users from closing tabs
        for (Tab tab : getTabs()) {
            tab.setClosable(false);
        }

        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }
}