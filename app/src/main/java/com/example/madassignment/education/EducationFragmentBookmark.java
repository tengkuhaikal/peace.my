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
import java.util.List;

public class EducationFragmentBookmark extends Fragment {
    RecyclerView recyclerView;
    //SearchView searchView;
    EducationAdapter educationAdapter;
    ArrayList<EducationItem> educationItemArrayList = new ArrayList<>();
    ArrayList<EducationItem> filteredList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.education_fragment_bookmark, container, false);

        recyclerView = view.findViewById(R.id.recyclerView); // Update with correct ID from layout

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Populate educationItemArrayList with data
        populateEducationList();

        educationAdapter = new EducationAdapter(getContext(), educationItemArrayList);
        recyclerView.setAdapter(educationAdapter);
        // setupSearchView();

        return view;
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
        // Create a background thread to fetch data from the database
        new Thread(() -> {
            // Initialize the database and DAO
            EducationAppDatabase db = EducationAppDatabase.getDatabase(requireContext());
            EducationItemDao educationItemDao = db.educationItemDao();

            // Fetch all items from the database
            List<EducationItem> items = educationItemDao.getAllItems();

            // Update the ArrayList on the main thread
            requireActivity().runOnUiThread(() -> {
                educationItemArrayList.clear();
                educationItemArrayList.addAll(items);
                educationAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
            });
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        populateEducationList(); // Refresh the data when the fragment is resumed
    }

}
