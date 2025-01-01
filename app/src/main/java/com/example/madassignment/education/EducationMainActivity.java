package com.example.madassignment.education;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.madassignment.R;
import com.example.madassignment.databinding.ActivityEducationMainBinding;


import java.util.ArrayList;

public class EducationMainActivity extends AppCompatActivity {


    ActivityEducationMainBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<ListData>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEducationMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        int[] iconList = {R.drawable.ic_diabetes, R.drawable.ic_hypertension, R.drawable.ic_asthma, R.drawable.ic_celiac_disease, R.drawable.ic_migraine, R.drawable.ic_osteoporosis, R.drawable.ic_depression, R.drawable.ic_anemia, R.drawable.ic_tuberculosis, R.drawable.ic_dengue, R.drawable.ic_malaria, R.drawable.ic_obesity, R.drawable.ic_hepatitis_b, R.drawable.ic_covid, R.drawable.ic_alzheimer};
        int[] imageList = {R.drawable.diabetes_mellitus, R.drawable.hypertension, R.drawable.asthma, R.drawable.celiac_disease, R.drawable.migraine, R.drawable.osteoporosis, R.drawable.depression, R.drawable.anemia, R.drawable.tuberculosis, R.drawable.dengue_fever, R.drawable.malaria, R.drawable.obesity, R.drawable.hepatitis_b, R.drawable.covid_19, R.drawable.alzheimers_disease};
        int[] descList = {R.string.diabetes_desc, R.string.hypertension_desc, R.string.asthma_desc, R.string.celiac_disease_desc, R.string.migraine_desc, R.string.osteoporosis_desc, R.string.depression_desc, R.string.anemia_desc, R.string.tuberculosis_desc, R.string.dengue_fever_desc, R.string.malaria_desc, R.string.obesity_desc, R.string.hepatitis_b_desc, R.string.covid_19_desc, R.string.alzheimers_disease_desc};
        int[] symptomsList = {R.string.diabetes_symptoms, R.string.hypertension_symptoms, R.string.asthma_symptoms, R.string.celiac_disease_symptoms, R.string.migraine_symptoms, R.string.osteoporosis_symptoms, R.string.depression_symptoms, R.string.anemia_symptoms, R.string.tuberculosis_symptoms, R.string.dengue_fever_symptoms, R.string.malaria_symptoms, R.string.obesity_symptoms, R.string.hepatitis_b_symptoms, R.string.covid_19_symptoms, R.string.alzheimers_disease_symptoms};
        int[] treatmentList = {R.string.diabetes_treatment, R.string.hypertension_treatment, R.string.asthma_treatment, R.string.celiac_disease_treatment, R.string.migraine_treatment, R.string.osteoporosis_treatment, R.string.depression_treatment, R.string.anemia_treatment, R.string.tuberculosis_treatment, R.string.dengue_fever_treatment, R.string.malaria_treatment, R.string.obesity_treatment, R.string.hepatitis_b_treatment, R.string.covid_19_treatment, R.string.alzheimers_disease_treatment};
        int[] nameList = {R.string.diabetes, R.string.hypertension, R.string.asthma, R.string.celiac_disease, R.string.migraine, R.string.osteoporosis, R.string.depression, R.string.anemia, R.string.tuberculosis, R.string.dengue_fever, R.string.malaria, R.string.obesity, R.string.hepatitis_b, R.string.covid_19, R.string.alzheimers_disease};


        for (int i = 0; i < imageList.length; i++) {
            listData = new ListData(nameList[i], imageList[i], descList[i], symptomsList[i], treatmentList[i], iconList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(EducationMainActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EducationMainActivity.this, DetailedActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("image", imageList[i]);
                intent.putExtra("desc", descList[i]);
                intent.putExtra("symptoms", symptomsList[i]);
                intent.putExtra("treatment", treatmentList[i]);
                intent.putExtra("icon", iconList[i]);
                startActivity(intent);
            }
        });
    }
}