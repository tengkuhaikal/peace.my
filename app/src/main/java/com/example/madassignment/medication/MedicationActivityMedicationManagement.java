package com.example.madassignment.medication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;


public class MedicationActivityMedicationManagement extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_activity_medication_management); // Link to the XML layout

        // Find the ImageView for back arrow navigation to ActivityChooseMedicationType
        ImageView backArrow = findViewById(R.id.arrow_back_button);
        backArrow.setOnClickListener(v -> {
            // Navigate to ActivityChooseMedicationType
            Intent intent = new Intent(MedicationActivityMedicationManagement.this, MedicationActivityChooseMedication.class);
            startActivity(intent);
        });

        // Find the ImageView for navigation to Reminder page
        ImageView arrowToReminder = findViewById(R.id.backButtonFromReminder);
        arrowToReminder.setOnClickListener(v -> {
            // Navigate to ActivityReminder
            Intent intent = new Intent(MedicationActivityMedicationManagement.this, MedicationActivityReminder.class);
            startActivity(intent);
        });

    }
}