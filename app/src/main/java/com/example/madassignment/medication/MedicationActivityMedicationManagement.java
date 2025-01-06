package com.example.madassignment.medication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.madassignment.R;
import com.example.madassignment.general.GeneralHomeFragment;

public class MedicationActivityMedicationManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medication_activity_medication_management);

        // Navigate back to ActivityChooseMedicationType
        ImageView backArrow = findViewById(R.id.arrow_back_button);
        backArrow.setOnClickListener(v -> {
            Intent intent = new Intent(MedicationActivityMedicationManagement.this, MedicationActivityChooseMedication.class);
            startActivity(intent);
        });

        // Navigate to Reminder page
        ImageView arrowToReminder = findViewById(R.id.backButtonFromReminder);
        arrowToReminder.setOnClickListener(v -> {
            Intent intent = new Intent(MedicationActivityMedicationManagement.this, MedicationActivityReminder.class);
            startActivity(intent);
        });

        // Navigate back to GeneralActivityHomePage
        ImageView backToHome = findViewById(R.id.IVArrowBackButton);
        backToHome.setOnClickListener(v -> {
            Intent intent = new Intent(MedicationActivityMedicationManagement.this, GeneralHomeFragment.class);
            startActivity(intent);
            finish();
        });
    }
}
