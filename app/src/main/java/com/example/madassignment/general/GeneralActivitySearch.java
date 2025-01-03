package com.example.madassignment.general;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.List;

public class GeneralActivitySearch extends GeneralBaseActivity {

    private static GeneralAppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity_search);

        database = Room.databaseBuilder(getApplicationContext(), GeneralAppDatabase.class, "app-database").build();

        String activePage = getIntent().getStringExtra("active_page");
        if ("search".equals(activePage)) {
            setActivePage(activePage);
        }

        setupSearchComponents();
    }

    private void setupSearchComponents() {
        AutoCompleteTextView searchBox = findViewById(R.id.AutoCompleteSearch);
        List<String> searchSuggestions = new ArrayList<>();
        searchSuggestions.add("Suggestion 1");
        searchSuggestions.add("Suggestion 2");
        searchSuggestions.add("Suggestion 3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, searchSuggestions);
        searchBox.setAdapter(adapter);

        searchBox.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = adapter.getItem(position);
            if (selectedItem != null) {
                performSearch(selectedItem);
            }
        });

        TextView noResultsTextView = findViewById(R.id.TVNoSearchResults);
        RecyclerView recyclerView = findViewById(R.id.RVSearchResults);

        noResultsTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<String> searchResults) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new GeneralSearchResultsAdapter(searchResults));
    }

    private void performSearch(String query) {
        new AsyncTask<String, Void, List<GeneralUser>>() {

            @Override
            protected List<GeneralUser> doInBackground(String... strings) {
                List<GeneralUser> results = database.generalUserDao().searchUsers("%" + strings[0] + "%");
                return results;
            }

            @Override
            protected void onPostExecute(List<GeneralUser> results) {
                updateSearchResults(results);
            }

        }.execute(query);
    }

    private void updateSearchResults(List<GeneralUser> results) {
        TextView noResultsTextView = findViewById(R.id.TVNoSearchResults);
        RecyclerView recyclerView = findViewById(R.id.RVSearchResults);

        if (results.isEmpty()) {
            noResultsTextView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noResultsTextView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            List<String> usernames = new ArrayList<>();
            for (GeneralUser generalUser : results) {
                usernames.add(generalUser.getUsername());
            }
            setupRecyclerView(recyclerView, usernames);
        }
    }
}

class GeneralSearchResultsAdapter extends RecyclerView.Adapter<GeneralSearchResultsAdapter.ViewHolder> {

    private List<String> results;

    public GeneralSearchResultsAdapter(List<String> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item_search_result_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.resultText.setText(results.get(position));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView resultText;

        public ViewHolder(View itemView) {
            super(itemView);
            resultText = itemView.findViewById(R.id.TVSearchResult);
        }
    }
}