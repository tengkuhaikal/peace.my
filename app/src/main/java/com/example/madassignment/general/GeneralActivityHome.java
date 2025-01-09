package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madassignment.R;
import com.example.madassignment.appointment.AppointmentActivityMain;
import com.example.madassignment.calendar.CalendarActivityMain;
import com.example.madassignment.education.EducationActivityMain;
import com.example.madassignment.medication.MedicationActivityMedicationManagement;
import com.example.madassignment.symptom.SymptomTrackingMainActivity;

import java.util.Arrays;
import java.util.List;

public class GeneralActivityHome extends GeneralBaseActivity {

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
        setContentView(R.layout.general_activity_home_page);

        ImageView homeNews = findViewById(R.id.IVHomeNews);
        TextView homeNewsTitle = findViewById(R.id.TVHomeNewsTitle);

        startNewsUpdates(homeNews, homeNewsTitle);

        // Symptom Tracking Button
        ImageButton symptomTrackingButton = findViewById(R.id.IBHomeSymptomTracking);
        symptomTrackingButton.setOnClickListener(v ->
                startActivity(new Intent(this, SymptomTrackingMainActivity.class))
        );

        // Medication Management Button
        ImageButton medicationManagementButton = findViewById(R.id.IBHomeMedicationManagement);
        medicationManagementButton.setOnClickListener(v ->
                startActivity(new Intent(this, MedicationActivityMedicationManagement.class))
        );

        // Education Button
        ImageButton educationButton = findViewById(R.id.IBHomeEducation);
        educationButton.setOnClickListener(v ->
                startActivity(new Intent(this, EducationActivityMain.class))
        );

        // Calendar Button
        ImageButton calendarButton = findViewById(R.id.IBHomeCalendar);
        calendarButton.setOnClickListener(v ->
                startActivity(new Intent(this, CalendarActivityMain.class))
        );

        // Appointment Button
        ImageButton appointmentButton = findViewById(R.id.IBHomeAppointment);
        appointmentButton.setOnClickListener(v ->
                startActivity(new Intent(this, AppointmentActivityMain.class))
        );

        // No need to call setActivePage here, it's handled by GeneralBaseActivity
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
}
