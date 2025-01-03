package com.example.madassignment.education;

public class EducationItem {
    String name;
    int image, icon;
    String description, symptoms, treatment;

    public EducationItem(String name, int image, String description, String symptoms, String treatment, int icon) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.icon = icon;
    }

    public EducationItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
