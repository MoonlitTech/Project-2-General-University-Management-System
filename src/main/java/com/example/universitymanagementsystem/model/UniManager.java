package com.example.universitymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class UniversityManager {
    private final List<Course> courses = new ArrayList<>();

    public UniversityManager() {
        courses.add(new Course("CS101", "Intro to Programming", 3));
        courses.add(new Course("CS201", "Data Structures", 3));
        courses.add(new Course("CS301", "Algorithms", 3));
        courses.add(new Course("MA101", "Calculus I", 4));
        courses.add(new Course("EN101", "English Composition", 3));
    }

    public boolean addStudent(Student student) { return true; }

    public Student findStudent(String id) { return null; }

    public List<Course> getAvailableCourses() { return new ArrayList<>(courses); }

    public Course findCourse(String code) { return null; }

    // The four methods below return null on success, or an error message on failure.
    public String registerCourse(String id, String code) { return null; }

    public String registerProject(String id, String title) { return null; }

    public String registerDissertation(String id, String title) { return null; }

    public String setGrade(String id, String code, double grade) { return null; }
}