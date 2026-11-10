package com.example.universitymanagementsystem.model;

public class MSStudent extends UndergraduateStudent {

    public MSStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerProject(String title) { }

    public String getProjectTitle() { return; }
}