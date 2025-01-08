package com.example.madassignment.symptom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SymptomViewDisplayFragment extends Fragment {

    private RecyclerView diseasesRecyclerView;
    private DiseaseAdapter diseaseAdapter;
    private List<String> selectedSymptoms;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.symptom_fragment_symptom_view_display, container, false);

        // Check if arguments are null
        Bundle args = getArguments();
        if (args == null) {
            Log.e("SymptomViewDisplay", "Arguments are null. Unable to retrieve selected symptoms.");
            return view; // Early return to prevent further issues
        }

        // Retrieve the selectedSymptoms list safely
        selectedSymptoms = args.getStringArrayList("selectedSymptoms");
        if (selectedSymptoms == null) {
            Log.e("SymptomViewDisplay", "Selected symptoms are null. Please ensure data is passed correctly.");
        } else {
            Log.d("SymptomViewDisplay", "Selected symptoms retrieved: " + selectedSymptoms.toString());
        }

        // Initialize RecyclerView
        diseasesRecyclerView = view.findViewById(R.id.diseasesRecyclerView);
        diseasesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        diseaseAdapter = new DiseaseAdapter(new ArrayList<>());
        diseasesRecyclerView.setAdapter(diseaseAdapter);

        // ViewModel setup
        DiseaseViewModel diseaseViewModel = new ViewModelProvider(this).get(DiseaseViewModel.class);

        // Observe disease list and update RecyclerView
        diseaseViewModel.getDiseasesLiveData().observe(getViewLifecycleOwner(), diseases -> {
            if (diseases != null) {
                // Debug log to check the diseases before filtering
                Log.d("SymptomViewDisplay", "All Diseases: " + diseases.toString());

                // Filter diseases based on selected symptoms
                List<DiseaseEntity> filteredDiseases = filterDiseasesBySymptoms(diseases, selectedSymptoms);
                // Debug log to check the filtered diseases
                Log.d("SymptomViewDisplay", "Filtered Diseases: " + filteredDiseases.toString());
                diseaseAdapter.updateDiseases(filteredDiseases);
            }
        });

        // Handle Next button click

        Button nextButton = view.findViewById(R.id.next_button3);
        nextButton.setOnClickListener(v -> {
            Fragment viewHistory_reportFragment = new SymptomViewHistoryFragment();

            // Pass the selected symptoms to the fragment using a Bundle
            Bundle bundle = new Bundle();
            if (selectedSymptoms != null) {
                bundle.putStringArrayList("selectedSymptoms", new ArrayList<>(selectedSymptoms));
            } else {
                Log.e("SymptomViewDisplay", "Selected symptoms are null, passing empty list to history fragment.");
                bundle.putStringArrayList("selectedSymptoms", new ArrayList<>());
            }
            viewHistory_reportFragment.setArguments(bundle);

            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, viewHistory_reportFragment);
            transaction.addToBackStack(null);
            transaction.commit();

            // Ensure tab appearance is updated
            ((SymptomTrackingMainActivity) getActivity()).updateTabAppearance(viewHistory_reportFragment);
        });

        return view;
    }

    // Method to filter diseases based on selected symptoms
    private List<DiseaseEntity> filterDiseasesBySymptoms(List<DiseaseEntity> allDiseases, List<String> selectedSymptoms) {
        List<DiseaseEntity> filteredDiseases = new ArrayList<>();
        for (DiseaseEntity disease : allDiseases) {
            if (diseaseMatchesSymptoms(disease, selectedSymptoms)) {
                filteredDiseases.add(disease);
            }
        }
        return filteredDiseases;
    }

    // Method to check if a disease matches the selected symptoms
    private boolean diseaseMatchesSymptoms(DiseaseEntity disease, List<String> selectedSymptoms) {
        return !disease.getRelatedSymptoms().stream()
                .filter(selectedSymptoms::contains)
                .collect(Collectors.toList()).isEmpty();
    }
}

