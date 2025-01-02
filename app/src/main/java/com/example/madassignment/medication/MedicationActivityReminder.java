package com.example.madassignment.medication;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.madassignment.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MedicationActivityReminder extends AppCompatActivity {

    private MedicationDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_activity_reminder); // Use activity_reminder.xml

        // Find the TextViews
        TextView todayTextView = findViewById(R.id.TVDay);
        TextView dateTimeTextView = findViewById(R.id.TVDateTime);
        TextView medicationsTextView = findViewById(R.id.medicationsList); // TextView for displaying medication list

        // Get the current date and time
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault()); // Day (e.g., Sunday)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()); // Date (e.g., 11 December 2024)
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault()); // Time (e.g., 10:30 AM)

        // Set the current day, date, and time
        String day = dayFormat.format(calendar.getTime());
        String date = dateFormat.format(calendar.getTime());
        String time = timeFormat.format(calendar.getTime());

        // Update the TextViews with current values
        todayTextView.setText(day);
        dateTimeTextView.setText(date + ", " + time); // Combine date and time

        // Set up the back arrow to handle back navigation
        ImageView backArrow = findViewById(R.id.backButtonFromReminder);
        backArrow.setOnClickListener(v -> onBackPressed());  // Go back to the previous activity

        // Initialize Room database
        db = Room.databaseBuilder(getApplicationContext(), MedicationDatabase.class, "medication-db").build();

        // Fetch and display the medications
        new Thread(() -> {
            List<Medication> medications = db.medicationDao().getAllMedications();
            StringBuilder medicationDetails = new StringBuilder();

            for (Medication medication : medications) {
                medicationDetails.append("Name: ").append(medication.getName())
                        .append("\nDosage: ").append(medication.getDosage())
                        .append("\nFrequency: ").append(medication.getFrequency())
                        .append("\n\n");
            }

            runOnUiThread(() -> medicationsTextView.setText(medicationDetails.toString()));
        }).start();
    }
}