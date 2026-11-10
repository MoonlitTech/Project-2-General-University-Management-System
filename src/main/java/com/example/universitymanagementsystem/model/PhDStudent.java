package com.example.universitymanagementsystem.model;

public class PhDStudent extends MSStudent {

    public PhDStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerDissertation(String title) { }

    public String getDissertationTitle() { return; }
}
