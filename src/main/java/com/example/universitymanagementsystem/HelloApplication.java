package com.example.universitymanagementsystem;

import com.example.universitymanagementsystem.gui.MainWindow;
import com.example.universitymanagementsystem.model.UniversityManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {

        // Create the university manager
        UniversityManager manager = new UniversityManager();

        // Create the main application window
        MainWindow mainWindow = new MainWindow(manager);

        // Set up the application scene
        Scene scene = new Scene(mainWindow, 950, 700);

        stage.setTitle("General University Management System");
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        stage.show();
    }
}
