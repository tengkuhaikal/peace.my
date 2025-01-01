package com.example.madassignment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MedicationCalendar extends AppCompatActivity {
    EditText medicationName;
    EditText dosage;
    EditText description;
    Button addMedication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_calendar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        medicationName = findViewById(R.id.etMedicationName);
        dosage = findViewById(R.id.etDossage);
        description = findViewById(R.id.etDescription);
        addMedication = findViewById(R.id.btnAddMedication);

        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!medicationName.getText().toString().isEmpty() && !dosage.getText().toString().isEmpty() && !description.getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);

                    intent.putExtra(CalendarContract.Events.TITLE, medicationName.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, dosage.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(MedicationCalendar.this, "There is no app that can support this action", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(MedicationCalendar.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}