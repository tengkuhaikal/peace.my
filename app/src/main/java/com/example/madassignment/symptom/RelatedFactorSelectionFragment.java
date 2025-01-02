package com.example.madassignment.symptom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madassignment.R;

import java.util.Arrays;
import java.util.List;

public class RelatedFactorSelectionFragment extends Fragment {
    private RecyclerView factorsRecyclerView;
    private FactorAdapter factorsAdapter;
    private List<String> factorsList; // This will hold dynamic factors like "Cold Weather", etc.

    public RelatedFactorSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.symptom_fragment_related_factor_selection, container, false);

        // Initialize RecyclerView
        factorsRecyclerView = view.findViewById(R.id.factors_recycler_view);
        factorsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Use vertical layout

        // Create data source for the RecyclerView
        factorsList = getFactorsList(); // You can fetch this from a database or define it manually

        // Initialize adapter
        factorsAdapter = new FactorAdapter(factorsList);

        // Set adapter to RecyclerView
        factorsRecyclerView.setAdapter(factorsAdapter);

        Button submitButton = view.findViewById(R.id.submit_button);
        View.OnClickListener button = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform the fragment transaction
                Fragment viewDisplay3 = new SymptomViewDisplayFragment();
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();

                // Replace the current fragment with the new fragment
                transaction.replace(R.id.fragment_container, viewDisplay3);
                transaction.addToBackStack(null); // Optional: adds to the back stack for "Back" navigation
                transaction.commit(); // Commit the transaction

                // This ensures the tab appearance is updated after navigating
                ((SymptomTrackingMainActivity) getActivity()).updateTabAppearance(viewDisplay3);
            }
        };
        submitButton.setOnClickListener(button);

        return view;
    }

    // Method to fetch factors dynamically (you can modify this to load data from a database)
    private List<String> getFactorsList() {
        return Arrays.asList("Cold Weather", "Allergy", "Stress", "Heat", "Infection");
    }
}

//no button handled