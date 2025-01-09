package com.example.madassignment.general;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.example.madassignment.R;
import com.example.madassignment.calendar.CalendarActivityMain;
import com.example.madassignment.education.EducationActivityMain;
import com.example.madassignment.medication.MedicationActivityMedicationManagement;
import com.example.madassignment.symptom.SymptomTrackingMainActivity;
import com.example.madassignment.appointment.AppointmentActivityMain;
import java.util.Arrays;
import java.util.List;

public class GeneralHomeFragment extends Fragment {

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

    private ImageView homeNews;
    private TextView homeNewsTitle;
    private ImageButton symptomTrackingButton;
    private ImageButton medicationManagementButton;
    private ImageButton educationButton;
    private ImageButton calendarButton;
    private ImageButton appointmentButton;
    public GeneralHomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_activity_home_page, container, false);

        homeNews = rootView.findViewById(R.id.IVHomeNews);
        homeNewsTitle = rootView.findViewById(R.id.TVHomeNewsTitle);
        symptomTrackingButton = rootView.findViewById(R.id.IBHomeSymptomTracking);
        medicationManagementButton = rootView.findViewById(R.id.IBHomeMedicationManagement);
        educationButton = rootView.findViewById(R.id.IBHomeEducation);
        calendarButton = rootView.findViewById(R.id.IBHomeCalendar);
        appointmentButton = rootView.findViewById(R.id.IBHomeAppointment);

        startNewsUpdates();

        symptomTrackingButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), SymptomTrackingMainActivity.class))
        );
        medicationManagementButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), MedicationActivityMedicationManagement.class))
        );
        educationButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), EducationActivityMain.class))
        );
        calendarButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), CalendarActivityMain.class))
        );
        appointmentButton.setOnClickListener(v ->
                startActivity(new Intent(getActivity(), AppointmentActivityMain.class))
        );

        return rootView;
    }

    private void startNewsUpdates() {
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
    public void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
    }
}
