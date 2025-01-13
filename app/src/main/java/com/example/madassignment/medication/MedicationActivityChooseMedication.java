package com.example.madassignment.medication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.madassignment.R;

public class MedicationActivityChooseMedication extends AppCompatActivity {

    private EditText medicationName, dosage, frequency, timeTaken;
    private MedicationDatabase db; // room database to store medication data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_activity_choose_medication);

        // user input
        medicationName = findViewById(R.id.medicationName);
        dosage = findViewById(R.id.dosage);
        frequency = findViewById(R.id.frequency);
        timeTaken = findViewById(R.id.timeTaken);

        // Initialize Room database ---> a room database instantiate with the name medication-db.it provide data storage to store medication data
        db = Room.databaseBuilder(getApplicationContext(), MedicationDatabase.class, "medication-db").build();

        // Handle "Set" button click
        Button setButton = findViewById(R.id.setButton);
        setButton.setOnClickListener(v -> {
            String name = medicationName.getText().toString().trim();
            String medDosage = dosage.getText().toString().trim();
            String medFrequency = frequency.getText().toString().trim();
            String medTime = timeTaken.getText().toString().trim();

            if (name.isEmpty() || medDosage.isEmpty() || medFrequency.isEmpty() || medTime.isEmpty()) {
                // Display error if any field is empty
                Toast.makeText(MedicationActivityChooseMedication.this, "Please fill out all fields!", Toast.LENGTH_SHORT).show();
            } else {
                // Save the data to the Room database in a background thread
                new Thread(() -> {
                    Medication medication = new Medication(name, medDosage, medFrequency, medTime);
                    db.medicationDao().insertMedication(medication);

                    // Notify user and reset fields
                    runOnUiThread(() -> {
                        Toast.makeText(MedicationActivityChooseMedication.this, "Medication saved successfully!", Toast.LENGTH_SHORT).show();
                        medicationName.setText("");
                        dosage.setText("");
                        frequency.setText("");
                        timeTaken.setText("");
                    });
                }).start();

                // Redirect back to ActivityMedicationManagement
                Intent intent = new Intent(MedicationActivityChooseMedication.this, MedicationActivityMedicationManagement.class);
                startActivity(intent);
            }
        });

        // Handle medication type buttons
        ImageButton pillButton = findViewById(R.id.pillButton);
        pillButton.setOnClickListener(v -> Toast.makeText(this, "Pill selected", Toast.LENGTH_SHORT).show());

        ImageButton capsuleButton = findViewById(R.id.capsuleButton);
        capsuleButton.setOnClickListener(v -> Toast.makeText(this, "Capsule selected", Toast.LENGTH_SHORT).show());

        ImageButton fluidButton = findViewById(R.id.fluidButton);
        fluidButton.setOnClickListener(v -> Toast.makeText(this, "Fluid selected", Toast.LENGTH_SHORT).show());

        ImageButton injectionButton = findViewById(R.id.injectionButton);
        injectionButton.setOnClickListener(v -> Toast.makeText(this, "Injection selected", Toast.LENGTH_SHORT).show());
    }
}