package com.example.madassignment.general;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.madassignment.R;

import java.util.ArrayList;
import java.util.List;

public class GeneralSearchFragment extends Fragment {

    private GeneralAppDatabase database;

    public GeneralSearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.general_activity_search, container, false);


        database = Room.databaseBuilder(getContext(), GeneralAppDatabase.class, "app-database").build();


        setupSearchComponents(rootView);

        return rootView;
    }

    private void setupSearchComponents(View rootView) {
        AutoCompleteTextView searchBox = rootView.findViewById(R.id.AutoCompleteSearch);
        List<String> searchSuggestions = new ArrayList<>();
        searchSuggestions.add("Suggestion 1");
        searchSuggestions.add("Suggestion 2");
        searchSuggestions.add("Suggestion 3");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, searchSuggestions);
        searchBox.setAdapter(adapter);

        searchBox.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = adapter.getItem(position);
            if (selectedItem != null) {
                performSearch(selectedItem);
            }
        });

        TextView noResultsTextView = rootView.findViewById(R.id.TVNoSearchResults);
        RecyclerView recyclerView = rootView.findViewById(R.id.RVSearchResults);

        noResultsTextView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void setupRecyclerView(RecyclerView recyclerView, List<String> searchResults) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
        View rootView = getView();
        if (rootView == null) return;

        TextView noResultsTextView = rootView.findViewById(R.id.TVNoSearchResults);
        RecyclerView recyclerView = rootView.findViewById(R.id.RVSearchResults);

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

class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private List<String> results;

    public SearchResultsAdapter(List<String> results) {
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
