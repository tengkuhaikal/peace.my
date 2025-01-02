package com.example.madassignment.symptom;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.madassignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SymptomTrackingMainActivity extends AppCompatActivity {
    private TextView tabSymptom, tabFactors, tabCauses,tabHistory_Report;
    private ImageView backButton;
    private AppDatabase database;
    private SymptomViewModel symptomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_activity_main);
        Log.d("MainActivity", "onCreate completed");

        // Initialize ViewModel
        database = AppDatabase.getInstance(this);
        symptomViewModel = new ViewModelProvider(this, new SymptomViewModelFactory(database.symptomDao())).get(SymptomViewModel.class);

        // Insert sample symptoms
        symptomViewModel.insertSymptoms();

        // Observe symptoms from ViewModel
        symptomViewModel.getSymptomsLiveData().observe(this, symptoms -> {
            if (symptoms != null) {
                updateSymptomsUI(symptoms);
            }
        });

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        backButton = findViewById(R.id.toolbar_backButton);
        backButton.setOnClickListener(view -> onBackPressed());

        // Tabs Setup
        tabSymptom = findViewById(R.id.tab_symptom);
        tabFactors = findViewById(R.id.tab_factors);
        tabCauses = findViewById(R.id.tab_causes);
        tabHistory_Report = findViewById(R.id.tab_history_report);

        // Tab click listeners
        tabSymptom.setOnClickListener(v -> navigateToFragment(new SymptomSelectionFragment()));
        tabFactors.setOnClickListener(v -> navigateToFragment(new RelatedFactorSelectionFragment()));
        tabCauses.setOnClickListener(v -> navigateToFragment(new SymptomViewDisplayFragment()));
        tabHistory_Report.setOnClickListener(v -> navigateToFragment(new SymptomViewHistoryFragment()));

        // Bottom Navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Handle bottom navigation item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_item_home) {
                navigateToFragment(new SymptomSelectionFragment());
                return true;
            }
            return false;
        });

        // Initially load the symptom fragment
        if (savedInstanceState == null) {
            navigateToFragment(new SymptomSelectionFragment());
        }
    }

    private void navigateToFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment); // Replace the fragment container's content
        transaction.addToBackStack(null); // Add this transaction to the back stack
        transaction.commit(); // Commit the transaction

        // Update tab appearance after navigation
        updateTabAppearance(fragment);
    }

    public void updateTabAppearance(Fragment fragment) {
        // Update tab appearance based on selected tab
        if (fragment instanceof SymptomSelectionFragment) {
            setTabSelected(tabSymptom);
        } else if (fragment instanceof RelatedFactorSelectionFragment) {
            setTabSelected(tabFactors);
        } else if (fragment instanceof SymptomViewDisplayFragment) {
            setTabSelected(tabCauses);
        }else if(fragment instanceof SymptomViewHistoryFragment){
            setTabSelected(tabHistory_Report);
        }
    }
    private void setTabSelected(TextView selectedTab) {
        // Reset all tabs
        resetTab(tabSymptom);
        resetTab(tabFactors);
        resetTab(tabCauses);
        resetTab(tabHistory_Report);

        // Highlight the selected tab
        selectedTab.setTextColor(ContextCompat.getColor(this, R.color.font_color_light));
        selectedTab.setBackgroundResource(R.color.pressed_primary_btn_color);
        selectedTab.setTypeface(null, Typeface.BOLD);
    }
    private void resetTab(TextView tab) {
        tab.setTextColor(ContextCompat.getColor(this, R.color.font_color_dark));
        tab.setBackgroundResource(0);
        tab.setTypeface(null, Typeface.NORMAL);
    }

    private void updateSymptomsUI(List<SymptomEntity> symptoms) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("symptoms", new ArrayList<>(symptoms));
        SymptomSelectionFragment symptomSelectionFragment = new SymptomSelectionFragment();
        symptomSelectionFragment.setArguments(bundle);

        // Update UI by navigating to symptom fragment
        navigateToFragment(symptomSelectionFragment);
    }
}