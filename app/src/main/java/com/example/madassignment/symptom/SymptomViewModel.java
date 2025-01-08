package com.example.madassignment.symptom;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SymptomViewModel extends ViewModel {

    private final SymptomDao symptomDao;
    private final LiveData<List<SymptomEntity>> symptomHistory;

    public SymptomViewModel(SymptomDao symptomDao) {
        this.symptomDao = symptomDao;
        // Fetch symptom history from the database
        this.symptomHistory = symptomDao.getAllSymptoms();
    }

    public void insertSymptoms() {
        SymptomEntity[] symptoms = new SymptomEntity[] {
                new SymptomEntity("Wheezing", new Date()),
                new SymptomEntity("Shortness of breath",new Date()),
                new SymptomEntity("Constipation",new Date()),
                new SymptomEntity("Chest pain",new Date()),
                new SymptomEntity("Abdominal pain",new Date()),
                new SymptomEntity("Chronic joint pain",new Date()),
                new SymptomEntity("Nausea",new Date()),
                new SymptomEntity("Inflammation",new Date()),
                new SymptomEntity("Coughing",new Date()),
                new SymptomEntity("Diarrhea",new Date()),
                new SymptomEntity("Fever",new Date())
        };

        // Insert symptoms asynchronously
        new Thread(() -> {
            for (SymptomEntity symptom : symptoms) {
                // Check if the symptom already exists
                SymptomEntity existingSymptom = symptomDao.findByName(symptom.getName());
                if (existingSymptom == null) {
                    // If not, insert it
                    symptomDao.insert(symptom);
                }
            }
        }).start();
    }

    public LiveData<List<SymptomEntity>> getSymptomsLiveData() {
        return symptomHistory;
    }

    public LiveData<List<SymptomEntity>> getSymptomHistory(List<String> selectedSymptomNames) {
        if (selectedSymptomNames == null || selectedSymptomNames.isEmpty()) {
            return new MutableLiveData<>(new ArrayList<>()); // Return an empty list
        }
        return symptomDao.getSymptomHistory(selectedSymptomNames);
    }
}
