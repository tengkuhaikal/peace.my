package com.example.madassignment.education;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madassignment.R;

import java.util.ArrayList;

public class EducationFragmentAll extends Fragment {
    RecyclerView recyclerView;
    SearchView searchView;
    EducationAdapter educationAdapter;
    ArrayList<EducationItem> educationItemArrayList = new ArrayList<>();
    ArrayList<EducationItem> filteredList = new ArrayList<>();
    String[] names, descriptions, symptoms, treatments;
    int[] images, icons;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.education_fragment_all, container, false);

        recyclerView = view.findViewById(R.id.recyclerView); // Update with correct ID from layout
        searchView = view.findViewById(R.id.searchView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Populate educationItemArrayList with data
        populateEducationList();

        educationAdapter = new EducationAdapter(getContext(), educationItemArrayList);
        recyclerView.setAdapter(educationAdapter);
        setupSearchView();

        return view;
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterList(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });
    }

    void filterList(String query) {
        filteredList.clear();
        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(educationItemArrayList);
        } else {
            for (EducationItem item : educationItemArrayList) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        educationAdapter.updateList(filteredList);
    }

    private void populateEducationList() {
        names = new String[]{getString(R.string.alzheimers_disease), getString(R.string.diabetes), getString(R.string.hypertension), getString(R.string.asthma), getString(R.string.celiac_disease), getString(R.string.migraine), getString(R.string.osteoporosis), getString(R.string.depression), getString(R.string.anemia), getString(R.string.tuberculosis), getString(R.string.dengue_fever), getString(R.string.malaria), getString(R.string.obesity), getString(R.string.hepatitis_b), getString(R.string.covid_19)};
        descriptions = new String[]{getString(R.string.alzheimers_disease_desc), getString(R.string.diabetes_desc), getString(R.string.hypertension_desc), getString(R.string.asthma_desc), getString(R.string.celiac_disease_desc), getString(R.string.migraine_desc), getString(R.string.osteoporosis_desc), getString(R.string.depression_desc), getString(R.string.anemia_desc), getString(R.string.tuberculosis_desc), getString(R.string.dengue_fever_desc), getString(R.string.malaria_desc), getString(R.string.obesity_desc), getString(R.string.hepatitis_b_desc), getString(R.string.covid_19_desc)};
        symptoms = new String[]{getString(R.string.alzheimers_disease_symptoms), getString(R.string.diabetes_symptoms), getString(R.string.hypertension_symptoms), getString(R.string.asthma_symptoms), getString(R.string.celiac_disease_symptoms), getString(R.string.migraine_symptoms), getString(R.string.osteoporosis_symptoms), getString(R.string.depression_symptoms), getString(R.string.anemia_symptoms), getString(R.string.tuberculosis_symptoms), getString(R.string.dengue_fever_symptoms), getString(R.string.malaria_symptoms), getString(R.string.obesity_symptoms), getString(R.string.hepatitis_b_symptoms), getString(R.string.covid_19_symptoms)};
        treatments = new String[]{getString(R.string.alzheimers_disease_treatment), getString(R.string.diabetes_treatment), getString(R.string.hypertension_treatment), getString(R.string.asthma_treatment), getString(R.string.celiac_disease_treatment), getString(R.string.migraine_treatment), getString(R.string.osteoporosis_treatment), getString(R.string.depression_treatment), getString(R.string.anemia_treatment), getString(R.string.tuberculosis_treatment), getString(R.string.dengue_fever_treatment), getString(R.string.malaria_treatment), getString(R.string.obesity_treatment), getString(R.string.hepatitis_b_treatment), getString(R.string.covid_19_treatment)};
        images = new int[]{R.drawable.alzheimers_disease, R.drawable.diabetes_mellitus, R.drawable.hypertension, R.drawable.asthma, R.drawable.celiac_disease, R.drawable.migraine, R.drawable.osteoporosis, R.drawable.depression, R.drawable.anemia, R.drawable.tuberculosis, R.drawable.dengue_fever, R.drawable.malaria, R.drawable.obesity, R.drawable.hepatitis_b, R.drawable.covid_19};
        icons = new int[]{R.drawable.ic_alzheimer, R.drawable.ic_diabetes, R.drawable.ic_hypertension, R.drawable.ic_asthma, R.drawable.ic_celiac_disease, R.drawable.ic_migraine, R.drawable.ic_osteoporosis, R.drawable.ic_depression, R.drawable.ic_anemia, R.drawable.ic_tuberculosis, R.drawable.ic_dengue, R.drawable.ic_malaria, R.drawable.ic_obesity, R.drawable.ic_hepatitis_b, R.drawable.ic_covid};

        educationItemArrayList.clear();
        for (int i = 0; i < names.length; i++) {
            EducationItem educationItem = new EducationItem(names[i], images[i], descriptions[i], symptoms[i], treatments[i], icons[i]);
            educationItemArrayList.add(educationItem);
        }
        filteredList.addAll(educationItemArrayList);
    }
}
