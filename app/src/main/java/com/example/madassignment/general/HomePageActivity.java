package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madassignment.R;

import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends BaseActivity {

    private final Handler handler = new Handler();
    private int newsIndex = 0;

    private final List<Integer> newsImages = Arrays.asList(
            R.drawable.news_image_1,
            R.drawable.news_image_2
    );

    private final List<String> newsTitles = Arrays.asList(
            "Flood in Kelantan",
            "Dengue is spreading"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageView homeNews = findViewById(R.id.IVHomeNews);
        TextView homeNewsTitle = findViewById(R.id.TVHomeNewsTitle);

        startNewsUpdates(homeNews, homeNewsTitle);

        // Symptom Tracking Button
        ImageButton symptomTrackingButton = findViewById(R.id.IBHomeSymptomTracking);
        symptomTrackingButton.setOnClickListener(v ->
                startActivity(new Intent(this, SymptomTrackingActivity.class))
        );

        // Medication Management Button
        ImageButton medicationManagementButton = findViewById(R.id.IBHomeMedicationManagement);
        medicationManagementButton.setOnClickListener(v ->
                startActivity(new Intent(this, com.example.madassignment.medication.MedicationManagementActivity.class))
        );

        // Donor Pledge Button
        ImageButton donorPledgeButton = findViewById(R.id.IBHomeDonorPledge);
        donorPledgeButton.setOnClickListener(v ->
                startActivity(new Intent(this, DonorPledgeActivity.class))
        );

        // Education Button
        ImageButton educationButton = findViewById(R.id.IBHomeEducation);
        educationButton.setOnClickListener(v ->
                startActivity(new Intent(this, com.example.madassignment.education.EducationMainActivity.class))
        );

        // Calendar Button
        ImageButton calendarButton = findViewById(R.id.IBHomeCalendar);
        calendarButton.setOnClickListener(v ->
                startActivity(new Intent(this, com.example.madassignment.calendar.CalendarMainActivity.class))
        );

        setActivePage("home");
    }

    private void startNewsUpdates(ImageView homeNews, TextView homeNewsTitle) {
        // Update news image and title every 10 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                homeNews.setImageResource(newsImages.get(newsIndex));
                homeNewsTitle.setText(newsTitles.get(newsIndex));

                newsIndex = (newsIndex + 1) % newsImages.size();

                handler.postDelayed(this, 10000);
            }
        }, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }

    public void setActivePage(String page) {
        Intent intent = new Intent(this, BaseActivity.class);
        intent.putExtra("active_page", page);
        startActivity(intent);
    }
}