package com.example.madassignment.symptom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RelatedFactorSelectionFragment extends Fragment {
    public RelatedFactorSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.symptom_fragment_related_factor_selection, container, false);

        // Retrieve the selected symptoms passed from SymptomSelectionFragment
        Bundle args = getArguments();
        ArrayList<String> selectedSymptoms = null;
        if (args != null) {
            selectedSymptoms = args.getStringArrayList("selectedSymptoms");
            Log.d("RelatedFactorsSelection", "Received selected symptoms: " + selectedSymptoms);
        } else {
            Log.e("RelatedFactorsSelection", "Arguments are null. Unable to retrieve selected symptoms.");
        }

        // Set OnClickListeners for each factor (TextView)
        setFactorClickListener(view, R.id.factor1, "Cold Weather");
        setFactorClickListener(view, R.id.factor2, "Allergy");
        setFactorClickListener(view, R.id.factor3, "Stress");
        setFactorClickListener(view, R.id.factor4, "Heat");
        setFactorClickListener(view, R.id.factor5, "Infection");

        // Handle submit button click
        ArrayList<String> finalSelectedSymptoms = selectedSymptoms;
        Button submitButton = view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(v -> {
            // Navigate to the next fragment
            Fragment viewDisplay3 = new SymptomViewDisplayFragment();
            if (finalSelectedSymptoms != null) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("selectedSymptoms", finalSelectedSymptoms); // Forward the selected symptoms
                viewDisplay3.setArguments(bundle);
            }
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, viewDisplay3);
            transaction.addToBackStack(null); // Optional: adds to the back stack for "Back" navigation
            transaction.commit(); // Commit the transaction

            // Ensure tab appearance is updated
            ((SymptomTrackingMainActivity) getActivity()).updateTabAppearance(viewDisplay3);
        });

        return view;
    }

    // Method to set click listeners for each factor
    private void setFactorClickListener(View view, int factorId, String factorName) {
        TextView factorTextView = view.findViewById(factorId);
        factorTextView.setOnClickListener(v -> {
            // Display feedback or take action when a factor is clicked
            Toast.makeText(getContext(), "Selected: " + factorName, Toast.LENGTH_SHORT).show();
        });
    }
}
