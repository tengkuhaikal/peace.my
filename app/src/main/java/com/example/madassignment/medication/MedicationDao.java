package com.example.madassignment.medication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MedicationDao {

    // Insert method for adding a Medication object to the database
    @Insert
    void insertMedication(Medication medication);

    // Query to fetch all medications from the database
    @Query("SELECT * FROM medications")
    List<Medication> getAllMedications();
}
