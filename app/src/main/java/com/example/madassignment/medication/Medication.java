package com.example.madassignment.medication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "medications")  // Ensure the table name matches the one used in your query
public class Medication {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String dosage;
    private String frequency;

    // Constructor
    public Medication(String name, String dosage, String frequency) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
