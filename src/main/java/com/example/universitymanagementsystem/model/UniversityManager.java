package com.example.universitymanagementsystem.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UniversityManager {
    private final Map<String, Student> students = new LinkedHashMap<>();
    private final List<Course> courses = new ArrayList<>();

    public UniversityManager() {
        courses.add(new Course("CS101", "Intro to Programming", 3));
        courses.add(new Course("CS201", "Data Structures", 3));
        courses.add(new Course("CS301", "Algorithms", 3));
        courses.add(new Course("MA101", "Calculus I", 4));
        courses.add(new Course("EN101", "English Composition", 3));
    }

    public boolean addStudent(Student student) {
        if (students.containsKey(student.getId())) {
            return false;
        }
        students.put(student.getId(), student);
        return true;
    }

    public Student findStudent(String id) {
        return students.get(id);
    }

    public List<Course> getAvailableCourses() {
        return new ArrayList<>(courses);
    }

    public Course findCourse(String code) {
        for (Course c : courses) {
            if (c.getCode().equals(code)) {
                return c;
            }
        }
        return null;
    }

    // The four methods below return null on success, or an error message on failure.

    public String registerCourse(String id, String code) {
        Student student = findStudent(id);
        if (student == null) {
            return "No student with ID " + id + ".";
        }
        Course course = findCourse(code);
        if (course == null) {
            return "No course with code " + code + ".";
        }
        if (!student.registerCourse(course)) {
            return "Student is already registered for " + code + ".";
        }
        return null;
    }

    public String registerProject(String id, String title) {
        Student student = findStudent(id);
        if (student == null) {
            return "No student with ID " + id + ".";
        }
        if (!(student instanceof MSStudent)) {
            return "Only MS and PhD students can register for a project.";
        }
        ((MSStudent) student).registerProject(title);
        return null;
    }

    public String registerDissertation(String id, String title) {
        Student student = findStudent(id);
        if (student == null) {
            return "No student with ID " + id + ".";
        }
        if (!(student instanceof PhDStudent)) {
            return "Only PhD students can register for a dissertation.";
        }
        ((PhDStudent) student).registerDissertation(title);
        return null;
    }

    public String setGrade(String id, String code, double grade) {
        Student student = findStudent(id);
        if (student == null) {
            return "No student with ID " + id + ".";
        }
        boolean registered = false;
        for (Course c : student.getRegisteredCourses()) {
            if (c.getCode().equals(code)) {
                registered = true;
            }
        }
        if (!registered) {
            return "Student is not registered for course " + code + ".";
        }
        student.setGrade(code, grade);
        return null;
    }
}
