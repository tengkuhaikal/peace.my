package com.example.madassignment.education;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.madassignment.R;

public class EducationActivityDetailed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_activity_detailed);

        ImageView imageView = findViewById(R.id.detailedImage);
        TextView nameTextView = findViewById(R.id.detailedName);
        TextView descriptionTextView = findViewById(R.id.detailedDescription);
        TextView symptomsTextView = findViewById(R.id.detailedSymptoms);
        TextView treatmentTextView = findViewById(R.id.detailedTreatment);

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String symptoms = getIntent().getStringExtra("symptoms");
        String treatment = getIntent().getStringExtra("treatment");
        int image = getIntent().getIntExtra("image", R.drawable.diabetes_mellitus);

        imageView.setImageResource(image);
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        symptomsTextView.setText(symptoms);
        treatmentTextView.setText(treatment);
    }
}