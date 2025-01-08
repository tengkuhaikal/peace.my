package com.example.madassignment.symptom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomSelectionFragment extends Fragment {

    private SymptomViewModel symptomViewModel;
    private SymptomAdapter symptomAdapter;
    private List<String> selectedSymptoms = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.symptom_fragment_symptom_selection, container, false);

        // RecyclerView setup
        RecyclerView recyclerView = view.findViewById(R.id.symptom_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        symptomAdapter = new SymptomAdapter(new ArrayList<>());
        recyclerView.setAdapter(symptomAdapter);

        // ViewModel setup
        AppDatabase database = AppDatabase.getInstance(requireContext());
        symptomViewModel = new ViewModelProvider(this, new SymptomViewModelFactory(database.symptomDao()))
                .get(SymptomViewModel.class);

        // Observe data and update adapter
        symptomViewModel.getSymptomsLiveData().observe(getViewLifecycleOwner(), symptoms -> {
            if (symptoms != null) {
                symptomAdapter.updateSymptoms(symptoms);
            }
        });

        // Handle submit button click
        Button submitButton = view.findViewById(R.id.submit_button1);
        submitButton.setOnClickListener(v -> {
            selectedSymptoms = symptomAdapter.getSelectedSymptoms();
            Log.d("SymptomSelection", "Selected Symptoms: " + selectedSymptoms); // Debug log

            Bundle bundle = new Bundle();
            bundle.putStringArrayList("selectedSymptoms", new ArrayList<>(selectedSymptoms));

            Fragment relatedFactorsFragment = new RelatedFactorSelectionFragment();
            relatedFactorsFragment.setArguments(bundle);
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, relatedFactorsFragment);
            transaction.addToBackStack(null);
            transaction.commit();

            ((SymptomTrackingMainActivity) getActivity()).updateTabAppearance(relatedFactorsFragment);
        });

        return view;
    }
    // This method is called from MainActivity to update the symptom list
    public void updateSymptomsList(List<SymptomEntity> symptoms) {
        if (symptomAdapter != null) {
            symptomAdapter.updateSymptoms(symptoms); // Update the adapter with new symptoms
        }
    }
}
