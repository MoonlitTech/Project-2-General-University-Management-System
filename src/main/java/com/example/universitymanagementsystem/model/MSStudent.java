package com.example.universitymanagementsystem.model;

public class MSStudent extends UndergraduateStudent {
    private String projectTitle; // null until a project is registered

    public MSStudent(String lastName, String firstName, String phone, String id, String address) {
        super(lastName, firstName, phone, id, address);
    }

    public void registerProject(String title) {
        this.projectTitle = title;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    @Override
    public String getType() {
        return "MS";
    }

    @Override
    public String getInfo() {
        return super.getInfo()
                + "MS Project: " + (projectTitle == null ? "(none)" : projectTitle) + "\n";
    }
}
