package com.example.universitymanagementsystem.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {
    private final String lastName;
    private final String firstName;
    private final String phone;
    private final String id;
    private final String address;
    private final List<Course> registeredCourses = new ArrayList<>();
    private final Map<String, Double> grades = new HashMap<>(); // course code -> grade (0.0 to 4.0)

    public Student(String lastName, String firstName, String phone, String id, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phone = phone;
        this.id = id;
        this.address = address;
    }

    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getPhone() { return phone; }
    public String getId() { return id; }
    public String getAddress() { return address; }

    public List<Course> getRegisteredCourses() {
        return new ArrayList<>(registeredCourses);
    }

    public boolean registerCourse(Course course) {
        for (Course c : registeredCourses) {
            if (c.getCode().equals(course.getCode())) {
                return false;
            }
        }
        registeredCourses.add(course);
        return true;
    }

    public void setGrade(String courseCode, double grade) {
        grades.put(courseCode, grade);
    }

    public double getGPA() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (double g : grades.values()) {
            total += g;
        }
        return total / grades.size();
    }

    public String getType() {
        return "Undergraduate";
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(getType()).append(" Student\n");
        sb.append("Name: ").append(firstName).append(" ").append(lastName).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Phone: ").append(phone).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("GPA: ").append(String.format("%.2f", getGPA())).append("\n");
        sb.append("Registered courses:\n");
        if (registeredCourses.isEmpty()) {
            sb.append("  (none)\n");
        }
        for (Course c : registeredCourses) {
            Double grade = grades.get(c.getCode());
            sb.append("  ").append(c);
            if (grade != null) {
                sb.append(" - Grade: ").append(grade);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
