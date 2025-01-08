package com.example.madassignment.symptom;

import java.util.List;

public class DiseaseEntity {
    private String name;
    private String description;
    private List<String> relatedSymptoms;

    public DiseaseEntity(String name, String description, List<String>relatedSymptoms) {
        this.name = name;
        this.description = description;
        this.relatedSymptoms = relatedSymptoms;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getRelatedSymptoms(){
        return relatedSymptoms;
    }

    public void setRelatedSymptoms(List<String> relatedSymptoms) {
        this.relatedSymptoms = relatedSymptoms;
    }
}
