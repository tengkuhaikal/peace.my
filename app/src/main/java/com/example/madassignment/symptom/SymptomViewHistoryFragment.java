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
import java.util.List;
import java.util.Locale;

public class SymptomViewHistoryFragment extends Fragment {
    private SymptomViewModel symptomViewModel; // ViewModel to fetch symptom data
    private SymptomHistoryAdapter adapter;

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

        // Insert predefined symptoms (only for the first app run, optional)
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

        // Add logging to verify the retrieved symptoms
        if (selectedSymptoms != null && !selectedSymptoms.isEmpty()) {
            Log.d("SymptomViewHistory", "Selected Symptoms: " + selectedSymptoms);
        } else {
            Log.d("SymptomViewHistory", "No selected symptoms passed. Showing all symptoms.");
        }

        if (selectedSymptoms != null && !selectedSymptoms.isEmpty()) {
            // Observe only the selected symptoms from the database
            symptomViewModel.getSymptomHistory(selectedSymptoms).observe(getViewLifecycleOwner(), history -> {
                if (history != null && !history.isEmpty()) {
                    adapter.updateData(history);
                } else {
                    Toast.makeText(requireContext(), "No history found for selected symptoms.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            // Observe all symptoms if no specific symptoms were passed
            symptomViewModel.getSymptomsLiveData().observe(getViewLifecycleOwner(), history -> {
                if (history != null) {
                    adapter.updateData(history);
                }
            });
        }

        // Generate Report button
        Button generateReportButton = view.findViewById(R.id.generateReportButton);
        generateReportButton.setOnClickListener(v -> generateReport(adapter.getSymptoms()));

        return view;
    }

    /**
     * Method to generate a symptom history report.
     */
    private void generateReport(List<SymptomEntity> symptomHistory) {
        // Get the Downloads directory
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs(); // Ensure the directory exists
        }

        // Define the report file in the Downloads directory
        File reportFile = new File(downloadsDir, "symptom_history_report.txt");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write("Symptom History Report\n");
            writer.write("=======================\n");

            // Write each symptom with its date to the report
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            for (SymptomEntity symptom : symptomHistory) {
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
