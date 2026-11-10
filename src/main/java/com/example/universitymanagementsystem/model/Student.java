package com.example.universitymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Student {

    public Student(String lastName, String firstName, String phone, String id, String address) {

    }

    public String getLastName() { return; }
    public String getFirstName() { return; }
    public String getPhone() { return; }
    public String getId() { return; }
    public String getAddress() { return; }

    public List<Course> getRegisteredCourses() { return new ArrayList<>(); }

    public boolean registerCourse(Course course) { return true; }

    public void setGrade(String courseCode, double grade) { }

    public double getGPA() { return 0.0; }

    public String getType() { return; }

    public String getInfo() { return; }
}