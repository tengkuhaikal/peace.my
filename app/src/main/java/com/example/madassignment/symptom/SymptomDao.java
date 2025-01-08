package com.example.madassignment.symptom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SymptomDao {

    @Insert
    void insert(SymptomEntity symptom);

    @Query("SELECT * FROM symptoms WHERE name = :name LIMIT 1")
    SymptomEntity findByName(String name);

    @Query("SELECT * FROM symptoms")
    LiveData<List<SymptomEntity>> getAllSymptoms();

    @Query("SELECT * FROM symptoms WHERE name IN (:names)")
    LiveData<List<SymptomEntity>> getSymptomHistory(List<String> names);
}
