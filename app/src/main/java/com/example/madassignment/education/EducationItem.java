package com.example.madassignment.education;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "education_items")
public class EducationItem {
    @PrimaryKey
    @NonNull
    String name;
    @NonNull
    int image, icon;
    @NonNull
    String description, symptoms, treatment;

    public EducationItem(@NonNull String name, @NonNull int image, @NonNull String description, @NonNull String symptoms, @NonNull String treatment, @NonNull int icon) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.icon = icon;
    }

    public EducationItem() {
    }

    @NonNull
    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public int getIcon() {
        return icon;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getSymptoms() {
        return symptoms;
    }

    @NonNull
    public String getTreatment() {
        return treatment;
    }
}