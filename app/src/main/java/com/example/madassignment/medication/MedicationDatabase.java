package com.example.madassignment.medication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Medication.class}, version = 1)
public abstract class MedicationDatabase extends RoomDatabase {

    private static final String DB_NAME = "medication-db";
    private static MedicationDatabase instance;

    // Singleton to get the database instance
    public static synchronized MedicationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MedicationDatabase.class, DB_NAME).build();
        }
        return instance;
    }

    // Abstract method to get the DAO
    public abstract MedicationDao medicationDao();
}
