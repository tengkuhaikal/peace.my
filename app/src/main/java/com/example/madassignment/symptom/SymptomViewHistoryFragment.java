package com.example.madassignment.symptom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.madassignment.R;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SymptomViewHistoryFragment extends Fragment {
    private SymptomViewModel symptomViewModel; // ViewModel to fetch symptom data
    private SymptomHistoryAdapter adapter;
    private final List<SymptomEntity> cumulativeHistory = new ArrayList<>(); // Holds all observed symptoms

    public SymptomViewHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get SymptomDao from your database instance
        AppDatabase db = AppDatabase.getInstance(requireActivity().getApplication());
        SymptomDao symptomDao = db.symptomDao();

        // Initialize the ViewModel using the factory
        SymptomViewModelFactory factory = new SymptomViewModelFactory(symptomDao);
        symptomViewModel = new ViewModelProvider(this, factory).get(SymptomViewModel.class);

        // Optionally insert predefined symptoms (e.g., only on the first app run)
        symptomViewModel.insertSymptoms();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.symptom_fragment_symptom_view_history, container, false);

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.symptomHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SymptomHistoryAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Retrieve selected symptoms passed from another fragment
        List<String> selectedSymptoms = getArguments() != null ? getArguments().getStringArrayList("selectedSymptoms") : new ArrayList<>();

        // Log the retrieved symptoms for debugging
        if (selectedSymptoms != null && !selectedSymptoms.isEmpty()) {
            Log.d("SymptomViewHistory", "Selected Symptoms: " + selectedSymptoms);
        } else {
            Log.d("SymptomViewHistory", "No selected symptoms passed. Showing all symptoms.");
        }

        // Fetch and observe symptom history based on the input
        if (selectedSymptoms != null && !selectedSymptoms.isEmpty()) {
            symptomViewModel.getSymptomHistory(selectedSymptoms).observe(getViewLifecycleOwner(), history -> {
                if (history != null && !history.isEmpty()) {
                    adapter.updateData(history);
                    addToCumulativeHistory(history);
                } else {
                    Toast.makeText(requireContext(), "No history found for selected symptoms.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            symptomViewModel.getSymptomsLiveData().observe(getViewLifecycleOwner(), history -> {
                if (history != null) {
                    adapter.updateData(history);
                    addToCumulativeHistory(history);
                }
            });
        }

        // Generate Report button functionality
        Button generateReportButton = view.findViewById(R.id.generateReportButton);
        generateReportButton.setOnClickListener(v -> generateReport());

        return view;
    }

    // Method to add symptoms to the cumulative history list without duplicates
    private void addToCumulativeHistory(List<SymptomEntity> newSymptoms) {
        for (SymptomEntity symptom : newSymptoms) {
            if (!cumulativeHistory.contains(symptom)) { // Avoid duplicates
                cumulativeHistory.add(symptom);
            }
        }
    }

    /**
     * Method to generate a symptom history report.
     */
    private void generateReport() {
        if (cumulativeHistory.isEmpty()) {
            Toast.makeText(requireContext(), "No history available to generate the report.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get the Downloads directory
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs(); // Ensure the directory exists
        }

        // Generate a unique filename using a timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File reportFile = new File(downloadsDir, "symptom_history_report_" + timestamp + ".txt");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write("Symptom History Report\n");
            writer.write("=======================\n");

            // Write each symptom with its date to the report
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            for (SymptomEntity symptom : cumulativeHistory) {
                String date = symptom.getDate() != null ? dateFormat.format(symptom.getDate()) : "Unknown date";
                writer.write("- " + symptom.getName() + " (Date: " + date + ")\n");
            }

            // Notify the user about the saved report
            Toast.makeText(requireContext(), "Report saved at: " + reportFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Failed to generate report.", Toast.LENGTH_SHORT).show();
        }
    }
}
