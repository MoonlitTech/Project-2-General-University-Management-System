package com.example.universitymanagementsystem.model;

public class PhDStudent extends MSStudent {
    private String dissertationTitle;

    public PhDStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerDissertation(String title) {
        this.dissertationTitle = title;
    }

    public String getDissertationTitle() {
        return dissertationTitle;
    }

    @Override
    public String getType() {
        return "PhD";
    }

    @Override
    public String getInfo() {
        return super.getInfo()
                + "Dissertation: " + (dissertationTitle == null ? "(none)" : dissertationTitle) + "\n";
    }
}