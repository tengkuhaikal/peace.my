package com.example.madassignment.education;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madassignment.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;

public class EducationActivityDetailed extends AppCompatActivity {
    Executor executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_activity_detailed);

        ImageView imageView = findViewById(R.id.detailedImage);
        TextView nameTextView = findViewById(R.id.detailedName);
        TextView descriptionTextView = findViewById(R.id.detailedDescription);
        TextView symptomsTextView = findViewById(R.id.detailedSymptoms);
        TextView treatmentTextView = findViewById(R.id.detailedTreatment);
        ImageView iconIV = findViewById(R.id.icon);
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);  // Single button for Add/Remove

        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String symptoms = getIntent().getStringExtra("symptoms");
        String treatment = getIntent().getStringExtra("treatment");
        int image = getIntent().getIntExtra("image", R.drawable.diabetes_mellitus);
        int icon = getIntent().getIntExtra("icon", R.drawable.ic_diabetes);

        imageView.setImageResource(image);
        nameTextView.setText(name);
        descriptionTextView.setText(description);
        symptomsTextView.setText(symptoms);
        treatmentTextView.setText(treatment);
        iconIV.setImageResource(icon);

        // Check if the item is already in favorites
        checkIfFavorite(name, floatingActionButton);

        // OnClickListener for the floating action button
        floatingActionButton.setOnClickListener(v -> {
            new Thread(() -> {
                EducationAppDatabase db = EducationAppDatabase.getDatabase(getApplicationContext());
                EducationItemDao educationItemDao = db.educationItemDao();
                EducationItem favoriteItem = new EducationItem(name, image, description, symptoms, treatment, icon);

                EducationItem existingItem = educationItemDao.getItemByName(name);

                if (existingItem != null) {
                    // If the item is already in favorites, remove it
                    educationItemDao.deleteFavorite(favoriteItem);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Removed from Bookmarks!", Toast.LENGTH_SHORT).show();
                        // Update the button icon to show "Add to Favorites"
                        floatingActionButton.setImageResource(R.drawable.bookmark_unfilled); // Change to add icon
                    });
                } else {
                    // If the item is not in favorites, add it
                    educationItemDao.insertFavorite(favoriteItem);
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Added to Bookmarks!", Toast.LENGTH_SHORT).show();
                        // Update the button icon to show "Remove from Favorites"
                        floatingActionButton.setImageResource(R.drawable.bookmark_filled); // Change to remove icon
                    });
                }
            }).start();
        });
    }

    // Method to check if the item is already a favorite and update the button icon accordingly
    private void checkIfFavorite(String name, FloatingActionButton floatingActionButton) {
        new Thread(() -> {
            EducationAppDatabase db = EducationAppDatabase.getDatabase(getApplicationContext());
            EducationItemDao educationItemDao = db.educationItemDao();
            EducationItem existingItem = educationItemDao.getItemByName(name);

            runOnUiThread(() -> {
                if (existingItem != null) {
                    // Item is in favorites, update the button to show "Remove"
                    floatingActionButton.setImageResource(R.drawable.bookmark_filled); // Change to remove icon
                } else {
                    // Item is not in favorites, update the button to show "Add"
                    floatingActionButton.setImageResource(R.drawable.bookmark_unfilled); // Change to add icon
                }
            });
        }).start();
    }
}